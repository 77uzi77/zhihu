package com.yidong.zhihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 回答类
 */
@Data
public class Answer implements Serializable {

    private Integer id;
    private String content;

    private Integer auser_id;

    private Integer aquestion_id;

//    @JsonIgnoreProperties(value = { "handler" })
    private Question question;

}
