package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Comment;
import com.yidong.zhihu.entity.vo.CommentVo;
import com.yidong.zhihu.entity.vo.ReplyVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    /**
     * 增加评论
     */
    @Insert("insert into comment (content,answer_id,reply_id,replied_id,pid,comment_time) " +
            "values (#{content},#{answer_id},#{reply_id},#{replied_id},#{pid},#{comment_time})")
    int addComment(Comment comment);

    /**
     * 根据 回答id 查找 一级评论
     */
    @Select("select id from comment where answer_id = #{answer_id} and pid = 0")
    List<Integer> getCommentIdByAnswerId(int answerId);

    /**
     * 通过 id 查找 一级评论的相关内容
     */
    @Select("select id,content,reply_id,comment_time from comment where id = #{commentId}")
    CommentVo selectCommentById(Integer commentId);

    /**
     * 通过 父id 查找 一级评论下的 二级评论
     */
    @Select("select id,content,reply_id,comment_time,replied_id from comment where pid = #{pid}")
    List<ReplyVo> selectByPid(Integer pid);
}
