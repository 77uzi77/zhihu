package com.yidong.zhihu.entity.vo;

import com.yidong.zhihu.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends User {

    private String code;

}
