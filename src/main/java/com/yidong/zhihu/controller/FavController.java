package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FavService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理收藏相关请求
 */
@RestController
@RequestMapping("fav")
public class FavController {

    @Autowired
    private FavService favService;

    /**
     * @param fav
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 收藏回答
     */
    @PostMapping("favAnswer")
    public ResultBean<?> favAnswer(@RequestBody Fav fav){
//        String token = request.getHeader("token");
//        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
//        fav.setUser_id(Integer.parseInt(id));
        return new ResultBean<>(favService.favAnswer(fav),ResultBean.SUCCESS_CODE);
    }

    /**
     * @param answerId
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     *  回答收藏数量
     */
    @GetMapping("favCount")
    public ResultBean<?> favCount(@RequestParam String answerId){
        return new ResultBean<>(favService.favCount(answerId));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param user_id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     *  查找我的收藏
     */
    @GetMapping("findMyFav")
    public ResultBean<?> findMyFav(int pageNum, int pageSize,int user_id) {
        return new ResultBean<>(favService.selectMyFavByPage(pageNum, pageSize, user_id));
    }

    /**
     * @param user_id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     *  查找我的收藏数量
     */
    @GetMapping("countMyFav")
    public ResultBean<?> countMyFav(int user_id){
        return new ResultBean<>(favService.countMyFav(user_id));
    }


    /**
     * @param user_id
     * @param answer_id
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/15
     *
     */
    @GetMapping("favState")
    public ResultBean<?> favState(Integer user_id,Integer answer_id){
        return new ResultBean<>(favService.favState(user_id,answer_id));
    }
}
