package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.Thumb;
import com.yidong.zhihu.service.ThumbService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理点赞相关请求
 */
@RestController
@RequestMapping("thumb")
public class ThumbController {
    @Autowired
    private ThumbService thumbService;

    /**
     * @param thumb
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 点赞回答
     */
    @PostMapping("thumbAnswer")
    public ResultBean<?> thumbAnswer(@RequestBody Thumb thumb){
//        String token = request.getHeader("token");
//        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
//        thumb.setUser_id(Integer.parseInt(id));
        return new ResultBean<>(thumbService.thumbAnswer(thumb));
    }

    /**
     * @param answerId
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     *  查找回答的点赞数量
     */
    @GetMapping("thumbCount")
    public ResultBean<?> thumbCount(@RequestParam String answerId){
        return new ResultBean<>(thumbService.thumbCount(answerId));
    }
}
