package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 评论类
 */
@Data
public class Comment implements Serializable {
    private Integer id;
    private String content;
    private Integer answer_id;
    private Integer reply_id;
    private Integer replied_id;
    private Integer pid;
    private String comment_time;

}
