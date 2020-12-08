package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.Thumb;
import com.yidong.zhihu.service.ThumbService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("thumb")
public class ThumbController {
    @Autowired
    private ThumbService thumbService;

    /**
     * 点赞回答
     */
    @PostMapping("thumbAnswer")
    public void thumbAnswer(HttpServletRequest request, @RequestBody Thumb thumb){
        String token = request.getHeader("token");
        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
        thumb.setUser_id(Integer.parseInt(id));
        thumbService.thumbAnswer(thumb);
    }

    /**
     * 回答 的 点赞数量
     */
    @GetMapping("thumbCount")
    public ResultBean<?> thumbCount(@RequestParam String answerId){
        return thumbService.thumbCount(answerId);
    }
}
