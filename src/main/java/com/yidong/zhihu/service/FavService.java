package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.ResultBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FavService {
    void favAnswer(Fav fav);

    ResultBean<?> favCount(String answerId);

    void transFavFromRedis2DB();

    ResultBean<?> selectMyFavByPage(int pageNum, int pageSize, int user_id);


}
