package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("sendCode")
    public ResultBean<String> sendEmail(@RequestParam("email") String email){
        if (userService.sendEmail(email)){
            return new ResultBean<>("发送验证码成功！请注意查收！");
        }else{
            return new ResultBean<>("发送邮件失败，请检查邮箱是否正确！");
        }
    }

    @PostMapping("/register")
    public ResultBean<String> register(@RequestBody UserVo user){
        if (userService.register(user)){
            return new ResultBean<>("注册成功！");
        }else{
            return new ResultBean<>("注册失败！");
        }
    }

    @PostMapping("/login")
    public ResultBean<com.alibaba.fastjson.JSONObject> login(@RequestBody User user){
        return new ResultBean<>(userService.login(user));
    }

    @PostMapping("/test")
    public ResultBean<String> test(String message) {
       /* Map<String,Object> map = new HashMap<>();
        map.put("state",true);
        map.put("msg","请求成功");*/
        System.out.println(message);
        return new ResultBean<>("请求成功！");/*map*/
    }

    //查询我的关注
    @GetMapping("findMyFos")
    public ResultBean<?> findMyAns(int pageNum, int pageSize,int follower_id) {
        return userService.selectMyFosByPage(pageNum, pageSize, follower_id);
    }

    //个人主页：查看个人基本信息
    @GetMapping("findSelfMessage")
    public ResultBean<?> findSelfMessage(String username) {
        return new ResultBean<>(userService.selectSelfMessage(username));
    }

    //个人主页：编辑个人基本信息
    @PostMapping("editSelfMessage")
    public ResultBean<String> editSelfMessage(int id, @RequestBody String message) {
        userService.editSelfMessage(id, message);
        return new ResultBean<>("个人信息编辑成功！");
    }

    //点击其他用户名昵称，访问他人主页

}
