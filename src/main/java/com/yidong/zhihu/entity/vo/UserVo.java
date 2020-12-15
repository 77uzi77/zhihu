package com.yidong.zhihu.entity.vo;

import com.yidong.zhihu.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author lzc
 * discription 用户与前台交互
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends User {

    private String code;          //注册的时的验证码

}
