package com.yidong.zhihu.entity;


import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String message;
    private String iconpath;

}
