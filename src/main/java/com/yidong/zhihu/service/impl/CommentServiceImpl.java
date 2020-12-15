package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.entity.Comment;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.CommentVo;
import com.yidong.zhihu.entity.vo.ReplyVo;
import com.yidong.zhihu.mapper.CommentMapper;
import com.yidong.zhihu.mapper.UserMapper;
import com.yidong.zhihu.service.CommentService;
import com.yidong.zhihu.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Value("${web.upload-path}")
    private String path;

    /**
     * 评论回答
     * @author lzc
     */
    @Override
    public boolean addComment(Comment comment) {
        if (comment.getPid() == null){
            comment.setPid(0);
        }
        comment.setComment_time(CommonUtils.getNowTime());

        return commentMapper.addComment(comment) == 1;
    }

    /**
     * 得到所有评论  封装为List
     * @author lzc
     */
    @Override
    public List<CommentVo> getAllComment(int answerId) {
        List<CommentVo> result = new ArrayList<>();
        User user;
        // 查找文章下所有的父级评论
        List<Integer> commentIdList = commentMapper.getCommentIdByAnswerId(answerId);
        for (Integer commentId : commentIdList) {
            CommentVo vo = commentMapper.selectCommentById(commentId);
            // 添加回复人的用户名 和 头像
            user = userMapper.findUsernameByUserId( vo.getReply_id());
            vo.setReplyName(user.getUsername());
            if (user.getIconpath() != null){
                vo.setIcon(path + user.getIconpath());
            }
            //查找所有父级评论下的子评论
            List<ReplyVo> vos = commentMapper.selectByPid(vo.getId());
//            List<ReplyVo> reply = new ArrayList<>();
            for (ReplyVo replyVo : vos) {
                // 添加回复人的用户名 和 头像
                user = userMapper.findUsernameByUserId(replyVo.getReply_id());
                replyVo.setReplyName(user.getUsername());
                if (user.getIconpath() != null){
                    replyVo.setReply_icon(path + user.getIconpath());
                }
                // 添加被回复人的用户名 和 头像
                user = userMapper.findUsernameByUserId(replyVo.getReplied_id());
                replyVo.setReplied_name(user.getUsername());
                if (user.getIconpath() != null){
                    replyVo.setReplied_icon(path + user.getIconpath());
                }
            }
            vo.setReplyVo(vos);
            result.add(vo);
        }

        return result;
    }
}
