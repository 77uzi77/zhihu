package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("sendCode")
    public ResultBean<String> sendEmail(String email){
        if (userService.sendEmail(email)){
            return new ResultBean<>("发送验证码成功！请注意查收！");
        }else{
            return new ResultBean<>("发送邮件失败，请检查邮箱是否正确");
        }
    }

    @PostMapping("register")
    public ResultBean<String> register(UserVo user){
        if (userService.register(user)){
            return new ResultBean<>("注册成功！");
        }else{
            return new ResultBean<>("注册失败！");
        }
    }
}
