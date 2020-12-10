package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 关注类
 */
@Data
public class Follow implements Serializable {
    private Integer id;
    private Integer followstate;
    private Integer follower_id;
    private Integer followed_id;

}
