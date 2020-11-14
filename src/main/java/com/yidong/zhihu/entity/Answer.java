package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Answer implements Serializable {
    private Integer id;
    private String content;
    private Integer auser_id;
    private Integer aquestion_id;
}
