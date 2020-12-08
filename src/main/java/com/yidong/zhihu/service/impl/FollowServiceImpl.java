package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.constant.DataKey;
import com.yidong.zhihu.entity.Follow;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.mapper.FollowMapper;
import com.yidong.zhihu.service.FollowService;
import com.yidong.zhihu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FollowMapper followMapper;

    /**
     * 关注 该用户
     */
    @Override
    public void followOne(Follow follow) {
        String builder = follow.getFollowed_id() + ":" + follow.getFollower_id();
        redisUtil.hset(DataKey.USER_FOLLOW, builder,follow.getFollowstate());
        int count = follow.getFollowstate() == 1 ? 1 : -1;
        redisUtil.hincr(DataKey.USER_FOLLOW_COUNT,String.valueOf(follow.getFollowed_id()),count);
    }

    /**
     * 该用户的 关注数量
     */
    @Override
    public ResultBean<?> followCount(String followed_id) {
        if (followed_id == null){
            return new ResultBean<>("请求错误！无效的请求！");
        }
        String count = String.valueOf(redisUtil.hget(DataKey.USER_FOLLOW_COUNT,followed_id));
        count = count == null ? "0" : count;
        return new ResultBean<>(count);
    }
    
    /**
     * 将 缓存在 redis 的 用户关注信息 保存到 数据库
     */
    @Override
    @Transactional
    public void transFollowFromRedis2DB() {
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.scanAll(DataKey.USER_FOLLOW);
        List<Follow> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedAnswerId
            String[] split = key.split(":");
            String followed_id = split[0];
            String follower_id = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 Follow 对象
            Follow userFollow = new Follow();
            userFollow.setFollowed_id(Integer.parseInt(followed_id));
            userFollow.setFollower_id(Integer.parseInt(follower_id));
            userFollow.setFollowstate(value);
            list.add(userFollow);

            //存到 list 后从 Redis 中删除
            redisUtil.hdel(DataKey.USER_FOLLOW,key);
        }
        for (Follow follow : list){
            try {
                int followState = followMapper.findByUserAndAnswerId(follow.getFollowed_id(),follow.getFollower_id());
                if (followState != follow.getFollowstate()){
                    followMapper.reviseFollow(follow);
                }
            }catch (Exception e){
                followMapper.addFollow(follow);
            }
        }
    }


}
