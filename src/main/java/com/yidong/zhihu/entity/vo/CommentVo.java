package com.yidong.zhihu.entity.vo;

import lombok.Data;

import java.util.List;


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
