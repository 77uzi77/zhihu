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

    private Integer id;                    //回答的id
    private String content;                //回答的内容
    private Integer auser_id;              //主动回答的用户id
    private Integer aquestion_id;          //被回答的问题id
//    @JsonIgnoreProperties(value = { "handler" })
    private Question question;             //被回答的问题实体

}
