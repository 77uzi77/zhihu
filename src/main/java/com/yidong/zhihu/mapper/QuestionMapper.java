package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper{

    /**
     *  提问
     */
    @Insert("insert into question (title,detail,quser_id,quser_name) values (#{title},#{detail},#{quser_id},#{quser_name})")
    int add(Question question);

    /**
     *  分页查询所有问题
     */
    @Select("select * from question")
    List<Question> selectPage();

    // 分页查询 我的提问 总记录数
    @Select("select count(*) from question where quser_name = #{quser_name}")
    int countMyQues(String quser_name);

    // 分页查询 我的提问 每页记录
//    @Select("select * from question where quser_name = #{quser_name}  limit #{start},#{rows}")
//    List<Question> findByPage(int start, int rows, String quser_name);


    // 分页查询 我的提问 每页记录
    @Select("select * from question where quser_name = #{quser_name}")
    List<Question> findMyQuestionByPage(String quser_name);

    // 根据标题查找对应的question实体
    @Select("select * from question where title = #{title}")
    Question FindQuestionByTitle(String title);

    @Select("select * from question where title like #{content} or detail like #{content}")
    List<Question> selectPageBySearch(String content);
}
