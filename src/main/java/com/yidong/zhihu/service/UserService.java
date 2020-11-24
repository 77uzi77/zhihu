package com.yidong.zhihu.service;

import com.alibaba.fastjson.JSONObject;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean sendEmail(String email);

    boolean register(UserVo user);

    JSONObject login(User user);

}
