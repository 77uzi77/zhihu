package com.yidong.zhihu.mapper;
import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.Fav;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
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
  //  @Select("select * from fav where user_id = #{user_id} and favstate=1")
//   @Select("select question.*,answer.*, fav.* from question, answer,fav " +
//           "where fav.favstate=1 and fav.answer_id=answer.id and answer.aquestion_id=question.id"+
//           " and user_id = #{user_id}")
//    List<Answer> selectMyFavByPage(int user_id);
    @Select("select answer.*, fav.answer_id from answer,fav where fav.favstate = 1 " +
                "and fav.answer_id = answer.id and user_id = #{user_id}")
    @Results(id = "answerMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "auser_id",property = "auser_id"),
            @Result(column = "aquestion_id",property = "aquestion_id"),
            @Result(property = "question",column = "aquestion_id",
                one = @One(select = "com.yidong.zhihu.mapper.QuestionMapper.selectQuestionById",fetchType = FetchType.EAGER))
    })
    List<Answer> selectMyFavByPage(int user_id);

    //个人主页：计算 我的收藏 总记录数
    @Select("select count(*) from fav where user_id = #{user_id} and favstate=1")
    int countMyFav(int user_id);

    @Select("select favstate from fav where user_id = #{user_id} and answer_id = #{answer_id}")
    Integer findFavState(Integer user_id, Integer answer_id);
}
