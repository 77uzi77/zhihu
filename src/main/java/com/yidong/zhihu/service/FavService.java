package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.Fav;

import java.util.List;

public interface FavService {
    String favAnswer(Fav fav);

    String favCount(String answerId);

    void transFavFromRedis2DB();

    List<Answer> selectMyFavByPage(int pageNum, int pageSize, int user_id);

    int countMyFav(int user_id);
}
