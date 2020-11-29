package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.Fav;
import com.yidong.zhihu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavMapper {

    /**
     *  查找 该用户 对该回答 的 收藏状态
     */
    @Select("select favstate from fav where user_id = #{user_id} and answer_id = #{answer_id}")
    int findByUserAndAnswerId(Integer user_id, Integer answer_id);

    /**
     *  更新收藏状态
     */
    @Update("update fav set favstate = #{favstate} where user_id = #{user_id} and answer_id = #{answer_id}")
    void reviseFav(Fav fav);

    /**
     *  新增收藏
     */
    @Insert("insert into fav (favstate,user_id,answer_id) values (#{favstate},#{user_id},#{answer_id})")
    void addFav(Fav fav);

    //个人主页：分页查询我的收藏夹
    @Select("select * from fav where user_id = #{user_id} and favstate=1")
    List<Answer> selectMyFavByPage(int user_id);
}
