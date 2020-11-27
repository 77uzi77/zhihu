package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.ResultBean;

public interface FavService {
    void favAnswer(Fav fav);

    ResultBean<?> favCount(String answerId);

    void transFavFromRedis2DB();
}
