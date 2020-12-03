package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import com.yidong.zhihu.service.UserService;
import com.yidong.zhihu.constant.MailPre;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     *  发送 验证码 到邮箱
     */
    @GetMapping("sendCode")
    public ResultBean<String> sendEmail(@RequestParam("email") String email, @RequestParam(value = "tag",defaultValue = "0")Integer tag){
        String pre ;
        if (tag == 0){
            //注册
            pre = MailPre.REGISTER;
        } else {
            //忘记密码
            pre = MailPre.RESET_PWD;
        }

        if (userService.sendEmail(pre,email)){
            return new ResultBean<>("发送验证码成功！请注意查收！");
        }else{
            return new ResultBean<>("发送邮件失败，请检查邮箱是否正确！");
        }
    }

    /**
     *  注册
     */
    @PostMapping("/register")
    public ResultBean<String> register(@RequestBody UserVo user){
        if (userService.register(user)){
            return new ResultBean<>("注册成功！");
        }else{
            return new ResultBean<>("注册失败！");
        }
    }

    /**
     *  登录
     */
    @PostMapping("/login")
    public ResultBean<com.alibaba.fastjson.JSONObject> login(@RequestBody User user){
        return new ResultBean<>(userService.login(user));
    }

    /**
     *  找回密码
     */
    @PutMapping("findPassword")
    public ResultBean<?> findPassword(@RequestBody UserVo userVo, HttpServletRequest request){
        if (userService.forgetPassword(userVo)){
            String token = request.getHeader("token");
            if (token != null){
                redisUtil.del(token);
            }
            return new ResultBean<>("找回密码成功！");
        }else{
            return new ResultBean<>("找回密码失败！");
        }
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
    public ResultBean<String> editSelfMessage(@RequestBody User user) {
        userService.editSelfMessage(user.getId(), user.getMessage());
        return new ResultBean<>("个人信息编辑成功！");
    }

    //点击其他用户名昵称，访问他人主页

}
