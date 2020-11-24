package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.Thumb;

public interface ThumbService {
    void thumbAnswer(Thumb thumb);

    ResultBean<?> thumbCount(String answerId);

    void transThumbFromRedis2DB();
}
