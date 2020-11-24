package com.yidong.zhihu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Thumb implements Serializable {
    private Integer id;
    private Integer thumbstate;
    private Integer user_id;
    private Integer answer_id;
}
