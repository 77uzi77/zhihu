package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Thumb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ThumbMapper {

    /**
     *  查找 该用户 在 该回答 下的点赞状态
     */
    @Select("select thumbstate from thumb where user_id = #{user_id} and answer_id = #{answer_id}")
    int findByUserAndAnswerId(Integer user_id, Integer answer_id);

    /**
     *  修改点赞状态
     */
    @Update("update thumb set thumbstate = #{thumbstate} where user_id = #{user_id} and answer_id = #{answer_id}")
    void reviseThumb(Thumb thumb);

    /**
     *  增加 点赞
     */
    @Insert("insert into thumb (thumbstate,user_id,answer_id) values (#{thumbstate},#{user_id},#{answer_id})")
    void addThumb(Thumb thumb);

    @Select("select thumbstate from thumb where user_id = #{user_id} and answer_id = #{answer_id}")
    Integer findThumbState(Integer user_id, Integer answer_id);

}
