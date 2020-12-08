package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Comment;
import com.yidong.zhihu.entity.vo.CommentVo;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    List<CommentVo> getAllComment(int answerId);

}
