package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 关注类
 */
@Data
public class Follow implements Serializable {
    private Integer id;             //关注的id
    private Integer followstate;    //关注状态
    private Integer follower_id;    //主动关注的用户id
    private Integer followed_id;    //被关注的用户id

}
