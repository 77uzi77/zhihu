package com.yidong.zhihu.controller;

import com.yidong.zhihu.constant.MailPre;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.service.UserService;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  处理用户相关请求
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param email
     * @param tag
     * @return ResultBean<String>
     * @author lzc
     * @date 2020/12/10
     *  发送验证码到邮箱
     */
    @GetMapping("sendCode")
    public ResultBean<String> sendEmail(@RequestParam("email") String email, @RequestParam(value = "tag",defaultValue = "0")Integer tag){
        if (userService.sendEmail(tag,email)){
            return new ResultBean<>("发送验证码成功！请注意查收！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("发送邮件失败，请检查邮箱是否正确！",ResultBean.SUCCESS_CODE);
        }
    }

    /**
     * @param user
     * @return ResultBean<String>
     * @author lzc
     * @date 2020/12/10
     * 注册
     */
    @PostMapping("/register")
    public ResultBean<String> register(@RequestBody UserVo user){
        if (userService.register(user)){
            return new ResultBean<>("注册成功！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("注册失败！",ResultBean.UNSPECIFIED_CODE);
        }
    }

    /**
     * @param user
     * @return ResultBean<JSONObject>
     * @author ly
     * @date 2020/12/10
     * 登录
     */
    @PostMapping("/login")
    public ResultBean<com.alibaba.fastjson.JSONObject> login(@RequestBody User user){
        return new ResultBean<>(userService.login(user));
    }

    /**
     * @param userVo
     * @param request
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 找回密码
     */
    @PutMapping("findPassword")
    public ResultBean<?> findPassword(@RequestBody UserVo userVo, HttpServletRequest request){
        if (userService.forgetPassword(userVo)){
            String token = request.getHeader("token");
            if (token != null){
                redisUtil.del(token);
            }
            return new ResultBean<>("找回密码成功！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("找回密码失败！",ResultBean.SUCCESS_CODE);
        }
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param follower_id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     * 查找我的关注
     */
    @GetMapping("findMyFos")
    public ResultBean<?> findMyFos(int pageNum, int pageSize,int follower_id) {
        return new ResultBean<>(userService.selectMyFosByPage(pageNum, pageSize, follower_id));
    }

    /**
     * @param username
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     * 个人主页
     */
    @GetMapping("findSelfMessage")
    public ResultBean<?> findSelfMessage(String username) {
        return new ResultBean<>(userService.selectSelfMessage(username));
}

    /**
     * @param user
     * @return ResultBean<String>
     * @author ly
     * @date 2020/12/10
     * 编辑个人简介
     */
    @PostMapping("editSelfMessage")
    public ResultBean<String> editSelfMessage(@RequestBody User user) {
        userService.editSelfMessage(user.getId(), user.getMessage());
        return new ResultBean<>("个人信息编辑成功！",ResultBean.SUCCESS_CODE);
    }

    /**
     * @param username
     * @return ResultBean<User>
     * @author ly
     * @date 2020/12/10
     * 根据用户的用户名找到该用户的信息：头像、背景图等等
     */
    @GetMapping("findUserByUsername")
    public ResultBean<User> findUserByUsername( String username) {
        User user = userService.findUserByUsername(username);
        return new ResultBean<>(user);
    }

}
