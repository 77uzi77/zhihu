package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Follow;
import com.yidong.zhihu.entity.ResultBean;
import org.springframework.transaction.annotation.Transactional;

public interface FollowService {
    void followOne(Follow follow);

    ResultBean<?> followCount(String answerId);

    @Transactional
    void transFollowFromRedis2DB();

  //  int countMyFollow(int followed_id);
}
