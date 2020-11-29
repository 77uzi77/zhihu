package com.yidong.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidong.zhihu.constant.DataKey;
import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.mapper.FavMapper;
import com.yidong.zhihu.service.FavService;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FavServiceImpl implements FavService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FavMapper favMapper;

    /**
     * 收藏回答
     */
    @Override
    public void favAnswer(Fav fav) {
        String builder = fav.getUser_id() + ":" + fav.getAnswer_id();
        redisUtil.hset(DataKey.USER_FAV, builder,fav.getFavstate());
        int count = fav.getFavstate() == 1 ? 1 : -1;
        redisUtil.hincr(DataKey.USER_FAV_COUNT,String.valueOf(fav.getAnswer_id()),count);
    }

    /**
     * 回答 的 收藏 数量
     */
    @Override
    public ResultBean<?> favCount(String answerId) {
        if (answerId == null){
            return new ResultBean<>("请求错误！无效的回答！");
        }
        String count = String.valueOf(redisUtil.hget(DataKey.USER_FAV_COUNT,answerId));
        count = count == null ? "0" : count;
        return new ResultBean<>(count);
    }


    /**
     * 将 缓存在 redis 的 用户收藏信息 保存到 数据库
     */
    @Override
    @Transactional
    public void transFavFromRedis2DB() {
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.scanAll(DataKey.USER_FAV);
        List<Fav> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedAnswerId
            String[] split = key.split(":");
            String likedUserId = split[0];
            String likedAnswerId = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 Fav 对象
            Fav userFav = new Fav();
            userFav.setUser_id(Integer.parseInt(likedUserId));
            userFav.setAnswer_id(Integer.parseInt(likedAnswerId));
            userFav.setFavstate(value);
            list.add(userFav);

            //存到 list 后从 Redis 中删除
            redisUtil.hdel(DataKey.USER_FAV,key);
        }
        for (Fav fav : list){
            try {
                int favState = favMapper.findByUserAndAnswerId(fav.getUser_id(),fav.getAnswer_id());
                if (favState != fav.getFavstate()){
                    favMapper.reviseFav(fav);
                }
            }catch (Exception e){
                favMapper.addFav(fav);
            }
        }
    }

    /**
     * 分页查询 我的收藏
     */
    @Override
    public ResultBean<?> selectMyFavByPage(int pageNum, int pageSize, int user_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> myAnsList = favMapper.selectMyFavByPage(user_id);
        PageInfo<Answer> pageList = new PageInfo<>(myAnsList);
        return new ResultBean<>(pageList.getList());
    }


}
