package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 收藏类
 */
@Data
public class Fav implements Serializable {

    private Integer id;            //收藏的id
    private Integer favstate;      //收藏的状态
    private Integer user_id;       //主动收藏的用户id
    private Integer answer_id;     //被收藏的回答id


}
