package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerMapper {
    @Select("select id from answer where auser_id = #{auser_id} and aquestion_id = #{aquestion_id}")
    int findAnswer(Integer auser_id, Integer aquestion_id);

    @Update("update answer set content = #{content} where auser_id = #{auser_id}")
    int reviseAnswer(Answer answer);

    @Insert("insert into answer (content,auser_id,aquestion_id) values (#{content},#{auser_id},#{aquestion_id})")
    int addAnswer(Answer answer);
}
