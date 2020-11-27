package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Fav;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FavMapper {

    @Select("select favstate from fav where user_id = #{user_id} and answer_id = #{answer_id}")
    int findByUserAndAnswerId(Integer user_id, Integer answer_id);

    @Update("update fav set favstate = #{favstate} where user_id = #{user_id} and answer_id = #{answer_id}")
    void reviseFav(Fav fav);

    @Insert("insert into fav (favstate,user_id,answer_id) values (#{favstate},#{user_id},#{answer_id})")
    void addFav(Fav fav);
}
