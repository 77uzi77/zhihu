package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.vo.AnswerVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {

    /**
     *  查找 该用户 在 该问题 下的回答
     */
    @Select("select id from answer where auser_id = #{auser_id} and aquestion_id = #{aquestion_id}")
    int findAnswer(Integer auser_id, Integer aquestion_id);

    /**
     *  修改回答
     */
    @Update("update answer set content = #{content} where auser_id = #{auser_id} and aquestion_id = #{aquestion_id]")
    int reviseAnswer(Answer answer);

    /**
     *  新增回答
     */
    @Insert("insert into answer (content,auser_id,aquestion_id) values (#{content},#{auser_id},#{aquestion_id})")
    int addAnswer(Answer answer);

    /**
     *  查询 该问题 下的所有回答
     * @param aquestion_id
     */
    @Select("select * from answer where aquestion_id = #{aquestion_id}")
    List<AnswerVo> selectPageByQuestion(int aquestion_id);

    //个人主页：分页查询我的回答
 //   @Select("select question.*,answer.* from answer,question where answer.auser_id = #{auser_id} and answer.aquestion_id=question.id")
    @Select("select * from answer where auser_id = #{auser_id}")
    @Results(id = "answerMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "auser_id",property = "auser_id"),
            @Result(column = "aquestion_id",property = "aquestion_id"),
            @Result(property = "question",column = "aquestion_id",
                    one = @One(select = "com.yidong.zhihu.mapper.QuestionMapper.selectQuestionById",fetchType = FetchType.EAGER))
    })
    List<Answer> selectMyAnsByPage(int auser_id);

    // 个人主页：计算 我的回答 总记录数
    @Select("select count(*) from answer where auser_id = #{auser_id}")
    int countMyAns(int auser_id);

}
