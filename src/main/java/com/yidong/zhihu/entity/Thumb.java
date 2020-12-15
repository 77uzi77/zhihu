package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 点赞类
 */
@Data
public class Thumb implements Serializable {
    private Integer id;            //点赞id
    private Integer thumbstate;    //点赞状态
    private Integer user_id;       //点赞的用户id
    private Integer answer_id;     //点赞的回答id
}
