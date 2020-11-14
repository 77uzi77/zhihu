package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    @Insert("insert into question (title,detail,quser_id,quser_name) values (#{title},#{detail},#{quser_id},#{quser_name})")
    int add(Question question);

    @Select("select * from question")
    List<Question> selectPage();

}
