package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Follow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FollowMapper {
    /**
     *  查找 该用户 对 特定用户的 关注状态
     */
    @Select("select followstate from follow where followed_id = #{followed_id} and follower_id = #{follower_id}")
    int findByUserAndAnswerId(Integer followed_id, Integer follower_id);

    /**
     *  更新关注状态
     */
    @Update("update follow set followstate = #{followstate} where followed_id = #{followed_id} and follower_id = #{follower_id}")
    void reviseFollow(Follow follow);

    /**
     *  新增关注
     */
    @Insert("insert into follow (followstate,followed_id,follower_id) values (#{followstate},#{followed_id},#{follower_id})")
    void addFollow(Follow follow);

    @Select("select followstate from follow where follower_id = #{follower_id} and followed_id = #{followed_id}")
    Integer findFollowState(Integer followed_id, Integer follower_id);

}
