package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 收藏类
 */
@Data
public class Fav implements Serializable {

    private Integer id;
    private Integer favstate;
    private Integer user_id;
    private Integer answer_id;


}
