package com.yidong.zhihu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzc
 * discription 评论与前台交互
 */
@Data
public class CommentVo {

    private Integer id;
    private String content;
    private Integer reply_id;
    private String replyName;
    private String comment_time;
    private List<ReplyVo> replyVo;

    private String icon;
}
