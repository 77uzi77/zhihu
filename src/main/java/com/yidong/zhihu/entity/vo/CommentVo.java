package com.yidong.zhihu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzc
 * discription 评论与前台交互
 */
@Data
public class CommentVo {

    private Integer id;                   //父评论的id
    private String content;               //父评论的内容
    private Integer reply_id;             //父评论者的id
    private String replyName;             //父评论者的用户名
    private String comment_time;          //评论时间
    private List<ReplyVo> replyVo;        //子评论的实体

    private String icon;                  //父评论者的头像
}
