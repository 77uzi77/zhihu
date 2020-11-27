package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FavService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("fav")
public class FavController {

    @Autowired
    private FavService favService;

    /**
     * 收藏回答
     */
    @PostMapping("favAnswer")
    public void favAnswer(HttpServletRequest request,@RequestBody Fav fav){
        String token = request.getHeader("token");
        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
        fav.setUser_id(Integer.parseInt(id));
        favService.favAnswer(fav);
    }

    /**
     * 回答 的 收藏数量
     */
    @GetMapping("favCount")
    public ResultBean<?> favCount(@RequestParam String answerId){
        return favService.favCount(answerId);
    }
}
