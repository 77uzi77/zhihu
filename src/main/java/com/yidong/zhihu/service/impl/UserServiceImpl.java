package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.exception.bizException.BizException;
import com.yidong.zhihu.mapper.UserMapper;
import com.yidong.zhihu.service.MailService;
import com.yidong.zhihu.service.UserService;
import com.yidong.zhihu.utils.PasswordUtil;
import com.yidong.zhihu.utils.RandomCodeUtil;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean sendEmail(String email) {
        User user = userMapper.findUserByEmail(email);
        if (user != null){
            throw new BizException("此邮箱在此系统已被绑定注册，请输入未被绑定过的邮箱");
        }
        String code = RandomCodeUtil.getCheckCode();
        String subject = "知乎系统";
        String context="尊敬的用户你好"+
                "你的验证码是:"+
                code+"\n你可以用这个验证码进行注册、修改你的绑定邮箱或者忘记密码验证,有效期为五分钟。如果不是你的本人操作请忽略此条信息！";
        if (mailService.sendMail(email,subject,context)) {
            redisUtil.set(email, code, 300);
            return true;
        }
        return false;
    }

    @Override
    public boolean register(UserVo user) {
        if (user.getEmail() == null || user.getUsername() == null || user.getCode() == null || user.getPassword() == null)  {
            throw new BizException("相关信息不能为空！");
        }
        if (userMapper.findUserByUsername(user.getUsername()) != null){
            throw new BizException("此用户名已存在，请重新注册");
        }

        String code = String.valueOf(redisUtil.get(user.getEmail()));
        if (redisUtil.getExpire(user.getEmail()) > 0 && user.getCode().equals(code)){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            user.setMessage("暂未填写");
            if (userMapper.saveUser(user) != 0){
                redisUtil.del(user.getEmail());
                return true;
            }else{
                return  false;
            }
        }else{
            throw new BizException("验证邮箱验证码错误，注册失败");
        }
    }
}
