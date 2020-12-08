package com.yidong.zhihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class Answer implements Serializable {
    private Integer id;
    private String content;
    private Integer auser_id;
    private Integer aquestion_id;

//    @JsonIgnoreProperties(value = { "handler" })
    private Question question;

//    private Integer q_id;
//    private Integer quser_id;
//    private String title;
//    private String detail;
//    private String quser_name;
}
