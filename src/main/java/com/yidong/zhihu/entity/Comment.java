package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 评论类
 */
@Data
public class Comment implements Serializable {
    private Integer id;                //评论的id
    private String content;            //评论的内容
    private Integer answer_id;         //被评论的回答id
    private Integer reply_id;          //评论者的id
    private Integer replied_id;        //被评论的用户id
    private Integer pid;               //该评论的父id
    private String comment_time;       //评论时间

}
