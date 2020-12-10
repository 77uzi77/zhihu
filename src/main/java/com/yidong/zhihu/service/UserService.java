package com.yidong.zhihu.service;

import com.alibaba.fastjson.JSONObject;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean sendEmail(Integer tag, String email);

    boolean register(UserVo user);

    JSONObject login(User user);

    List<User> selectMyFosByPage(int pageNum, int pageSize, int follower_id);

    //个人主页：查看个人基本信息
    User selectSelfMessage(String username);

    void editSelfMessage(int id, String message);

    Boolean forgetPassword(UserVo userVo);

    /**
     *  查找 用户名 查找用户
     */
    User findUserByUsername(String username);
}
