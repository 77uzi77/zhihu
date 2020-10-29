package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.vo.UserVo;

public interface UserService {
    boolean sendEmail(String email);

    boolean register(UserVo user);
}
