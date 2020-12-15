package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 问题类
 */
@Data
public class Question implements Serializable {

    private Integer id;             //问题id
    private Integer quser_id;       //提问者的id
    private String title;           //问题标题
    private String detail;          //问题的详情
    private String quser_name;      //提问者的用户名

}
