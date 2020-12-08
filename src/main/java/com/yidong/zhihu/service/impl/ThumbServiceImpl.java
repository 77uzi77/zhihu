package com.yidong.zhihu.service.impl;


import com.yidong.zhihu.constant.DataKey;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.entity.Thumb;
import com.yidong.zhihu.mapper.ThumbMapper;
import com.yidong.zhihu.service.ThumbService;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ThumbServiceImpl implements ThumbService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ThumbMapper thumbMapper;

    /**
     * 点赞 该回答
     */
    @Override
    public void thumbAnswer(Thumb thumb) {
        String builder = thumb.getUser_id() + ":" + thumb.getAnswer_id();
        redisUtil.hset(DataKey.USER_THUMB, builder,thumb.getThumbstate());
        int count = thumb.getThumbstate() == 1 ? 1 : -1;
        redisUtil.hincr(DataKey.USER_THUMB_COUNT,String.valueOf(thumb.getAnswer_id()),count);
    }

    /**
     * 该回答的 点赞 数量
     */
    @Override
    public ResultBean<?> thumbCount(String answerId) {
        if (answerId == null){
            return new ResultBean<>("请求错误！无效的回答！");
        }
        String count = String.valueOf(redisUtil.hget(DataKey.USER_THUMB_COUNT,answerId));
        count = count == null ? "0" : count;
        return new ResultBean<>(count);
    }


    /**
     * 将 缓存在 redis 的 用户点赞信息 保存到 数据库
     */
    @Override
    @Transactional
    public void transThumbFromRedis2DB() {
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.scanAll(DataKey.USER_THUMB);
        List<Thumb> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedAnswerId
            String[] split = key.split(":");
            String likedUserId = split[0];
            String likedAnswerId = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 Thumb 对象
            Thumb userLike = new Thumb();
            userLike.setUser_id(Integer.parseInt(likedUserId));
            userLike.setAnswer_id(Integer.parseInt(likedAnswerId));
            userLike.setThumbstate(value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisUtil.hdel(DataKey.USER_THUMB,key);
        }
        for (Thumb thumb : list){
            try {
                int thumbState = thumbMapper.findByUserAndAnswerId(thumb.getUser_id(),thumb.getAnswer_id());
                if (thumbState != thumb.getThumbstate()){
                    thumbMapper.reviseThumb(thumb);
                }
            }catch (Exception e){
                thumbMapper.addThumb(thumb);
            }
        }
    }


}
