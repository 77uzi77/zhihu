package com.yidong.zhihu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidong.zhihu.constant.MailPre;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.exception.bizException.BizException;
import com.yidong.zhihu.mapper.UserMapper;
import com.yidong.zhihu.service.MailService;
import com.yidong.zhihu.service.UserService;
import com.yidong.zhihu.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     *  发送验证码到用户邮箱
     */
    @Override
    public boolean sendEmail(String pre,String email) {
//        User user = userMapper.findUserByEmail(email);
//        // 检验邮箱
//        if (user != null){
//            throw new BizException("此邮箱在此系统已被绑定注册，请输入未被绑定过的邮箱");
//        }
        String code = RandomCodeUtil.getCheckCode();
        String subject = "知乎系统";
        String context="尊敬的用户你好"+
                "你的验证码是:"+
                code+"\n你可以用这个验证码进行注册,找回密码等，有效期为五分钟。如果不是你的本人操作请忽略此条信息！";
        if (mailService.sendMail(email,subject,context)) {
            redisUtil.set(pre + email, code, 300);
            return true;
        }
        return false;
    }

    /**
     *  用户注册
     */
    @Override
    public boolean register(UserVo user) {
        if (user.getEmail() == null || user.getUsername() == null || user.getCode() == null || user.getPassword() == null)  {
            throw new BizException("相关信息不能为空！");
        }
        if (!CommonUtils.isAlphaNumeric(user.getUsername())){
            throw new BizException("注册账户含有特殊字符，注册失败");
        }
        if (userMapper.findUserByUsername(user.getUsername()) != null){
            throw new BizException("此用户名已存在，请重新注册");
        }
        // 校验 验证码
        String code = String.valueOf(redisUtil.get(MailPre.REGISTER + user.getEmail()));
        if (redisUtil.getExpire(MailPre.REGISTER + user.getEmail()) > 0 && user.getCode().equals(code)){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            user.setMessage("暂未填写");
            if (userMapper.saveUser(user) != 0){
                redisUtil.del(MailPre.REGISTER + user.getEmail());
                return true;
            }else{
                return  false;
            }
        }else{
            throw new BizException("验证邮箱验证码错误，注册失败");
        }
    }

    /**
     * 登录
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JSONObject login(User user){
        String username = user.getUsername();
        User userDB;
        // 判断是邮箱登录 还是 用户名登录
        if (username.contains("@")){
            userDB = userMapper.findUserByEmail(username);
        }else{
            userDB = userMapper.findUserByUsername(username);
        }
        // 账号不存在
        if (userDB == null){
            throw new BizException("用户不存在");
        }
        // 进行密码校验
//      String findPassword = userMapper.findPasswordByUsername(username);
        String userPassword = PasswordUtil.encode(user.getPassword());
        if (!userPassword.equals(userDB.getPassword())){
            throw new BizException("密码错误");
        }
        //payload
        Map<String,String> payload = new HashMap<>();
        payload.put("id", String.valueOf(userDB.getId()));   //注意转换字符格式
        payload.put("username",userDB.getUsername());
//      payload.put("password",userDB.getPassword());
        //生成JWT令牌
        String token = JWTUtils.getToken(payload);
        System.out.println(userDB.getId());
        //将登录的信息保存到Redis
//        redisTemplate.opsForValue().set(String.valueOf(userDB.getId()),token);
        redisUtil.set(token,token);
        // 将相关信息返回给前端
        userDB.setPassword(null);
        JSONObject obj = new JSONObject();
//        obj.put("state",true);
//        obj.put("msg","认证成功");
        obj.put("token",token);
        obj.put("userInfo",userDB);

        return  obj;
    }

    /**
     * 分页 查询 我的关注
     */
    @Override
    public ResultBean<?> selectMyFosByPage(int pageNum, int pageSize, int follower_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> myAnsList = userMapper.selectMyFosByPage(follower_id);
        PageInfo<User> pageList = new PageInfo<>(myAnsList);
        return new ResultBean<>(pageList.getList());
    }

    /**
     * 查找 个人信息
     */
    @Override
    public ResultBean<User> selectSelfMessage(String username) {
        return new ResultBean<User>(userMapper.selectSelfMessage(username));
    }

    /**
     * 编辑个人简介
     */
    @Override
    public void editSelfMessage(int id , @RequestBody String message) {
        userMapper.editSelfMessage(id, message);
    }

    /**
     * 找回密码
     */
    @Override
    public Boolean forgetPassword(UserVo user) {
        if (user.getEmail() == null || user.getCode() == null || user.getPassword() == null)  {
            throw new BizException("相关信息不能为空！");
        }
        // 校验 验证码
        String code = String.valueOf(redisUtil.get(MailPre.RESET_PWD + user.getEmail()));
        if (redisUtil.getExpire(MailPre.RESET_PWD + user.getEmail()) > 0 && user.getCode().equals(code)){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            if (userMapper.revisePassword(user) != 0){
                redisUtil.del(MailPre.RESET_PWD + user.getEmail());
                return true;
            }else{
                return  false;
            }
        }else{
            throw new BizException("验证邮箱验证码错误，找回密码失败");
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

}
