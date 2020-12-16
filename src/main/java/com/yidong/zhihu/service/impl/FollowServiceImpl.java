package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.constant.DataKey;
import com.yidong.zhihu.entity.Follow;
import com.yidong.zhihu.exception.bizException.BizException;
import com.yidong.zhihu.mapper.FollowMapper;
import com.yidong.zhihu.service.FollowService;
import com.yidong.zhihu.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FollowServiceImpl implements FollowService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FollowMapper followMapper;

    /**
     * 关注 该用户
     * @author lzc
     */
    @Override
    public String followOne(Follow follow) {
        String builder = follow.getFollowed_id() + ":" + follow.getFollower_id();
        redisUtil.hset(DataKey.USER_FOLLOW, builder,follow.getFollowstate());
        int count = follow.getFollowstate() == 1 ? 1 : -1;
        redisUtil.hincr(DataKey.USER_FOLLOW_COUNT,String.valueOf(follow.getFollowed_id()),count);

        String msg;
        if (count == 1){
            msg = "关注成功！";
        }else{
            msg = "取消关注成功！";
        }
        return msg;
    }

    /**
     * 该用户的 关注数量
     * @author lzc
     */
    @Override
    public String followCount(String followed_id) {
        if (followed_id == null){
            throw new BizException("无效的请求！");
        }
        String count = String.valueOf(redisUtil.hget(DataKey.USER_FOLLOW_COUNT,followed_id));
        count = count == null ? "0" : count;
        return count;
    }
    
    /**
     * 将 缓存在 redis 的 用户关注信息 保存到 数据库
     * @author lzc
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

    /**
     * 得到关注的状态
     * @author lzc
     */
    @Override
    public Integer followState(Integer follower_id, Integer followed_id) {
        String builder = followed_id + ":" + follower_id;
        Integer state = (Integer) redisUtil.hget(DataKey.USER_FOLLOW, builder);
        if (state == null){
            state = followMapper.findFollowState(followed_id,follower_id);
            state = state == null ? 0 : state;
        }
        return state;
    }


}
