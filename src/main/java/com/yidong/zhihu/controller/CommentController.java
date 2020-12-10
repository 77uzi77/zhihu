package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Comment;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理评论相关请求
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*
     * @param comment
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 评论回答
     */
    @PostMapping("commentAnswer")
    public ResultBean<?> commentAnswer(@RequestBody Comment comment){
        if (commentService.addComment(comment)){
            return new ResultBean<>("评论成功！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("评论失败！",ResultBean.UNSPECIFIED_CODE);
        }
    }

    /*
     * @param answerId
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 得到所有评论
     */
    @GetMapping("getAllComment")
    public ResultBean<?> getAllComment(@RequestParam int answerId){
        return new ResultBean<>(commentService.getAllComment(answerId));
    }
}
