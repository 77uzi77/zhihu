package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {

    private Integer id;
    private Integer quser_id;
    private String title;
    private String detail;
    private String quser_name;

}
