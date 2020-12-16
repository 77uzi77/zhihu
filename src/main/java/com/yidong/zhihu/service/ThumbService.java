package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Thumb;

public interface ThumbService {
    String thumbAnswer(Thumb thumb);

    String thumbCount(String answerId);

    void transThumbFromRedis2DB();

    Integer thumbState(Integer user_id, Integer answer_id);
}
