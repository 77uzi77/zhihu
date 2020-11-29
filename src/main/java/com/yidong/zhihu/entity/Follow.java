package com.yidong.zhihu.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 关注
 */
@Data
public class Follow implements Serializable {
    private Integer id;
    private Integer followstate;
    private Integer follower_id;
    private Integer followed_id;

}
