package com.yidong.zhihu.entity.vo;

import lombok.Data;


/**
 * @author lzc
 * discription 作为二级评论
 */
@Data
public class ReplyVo {
    private Integer id;            //子评论的id
    private String content;        //子评论的内容
    private Integer reply_id;      //子评论者的id
    private String replyName;      //子评论者的用户名
    private String comment_time;   //子评论时间
    private String reply_icon;     //子评论者的头像

    private Integer replied_id;    //父评论者的id
    private String replied_name;   //父评论的用户名
    private String replied_icon;   //父评论者的头像

}
