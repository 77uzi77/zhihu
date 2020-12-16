package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Follow;
import org.springframework.transaction.annotation.Transactional;

public interface FollowService {
    String followOne(Follow follow);

    String followCount(String answerId);

    @Transactional
    void transFollowFromRedis2DB();

    Integer followState(Integer follower_id, Integer followed_id);

    //  int countMyFollow(int followed_id);
}
