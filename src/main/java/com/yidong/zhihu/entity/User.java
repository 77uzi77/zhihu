package com.yidong.zhihu.entity;


import lombok.Data;

import java.io.Serializable;


/**
 * @author lzc
 * discription 用户信息类
 */
@Data
public class User implements Serializable {

    private Integer id;       //用户id
    private String username;  //用户名
    private String email;     //用户邮箱
    private String password;  //密码
    private String message;   //用户个人主页的简介
    private String iconpath;  //头像
    private String bgppath;   //背景图

}
