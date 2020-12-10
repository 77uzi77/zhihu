package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Follow;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FollowService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  处理关注相关请求
 */
@RestController
@RequestMapping("follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    /*
     * @param request
     * @param follow
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 关注用户
     */
    @PostMapping("followOne")
    public ResultBean<?> followOne(@RequestBody Follow follow){
//        String token = request.getHeader("token");
//        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
//        follow.setFollower_id(Integer.parseInt(id));
        return new ResultBean<>(followService.followOne(follow));
    }

    /*
     * @param followed_id
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     *  查找 关注数量
     */
    @GetMapping("followCount")
    public ResultBean<?> followCount(@RequestParam String followed_id){
        return new ResultBean<>(followService.followCount(followed_id));
    }
}
