package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Follow;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FollowService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    /**
     * 关注
     */
    @PostMapping("followOne")
    public void followOne(HttpServletRequest request, @RequestBody Follow follow){
        String token = request.getHeader("token");
        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
        follow.setFollower_id(Integer.parseInt(id));
        followService.followOne(follow);
    }

    /**
     * 关注数量
     */
    @GetMapping("followCount")
    public ResultBean<?> followCount(@RequestParam String followed_id){
        return followService.followCount(followed_id);
    }
}
