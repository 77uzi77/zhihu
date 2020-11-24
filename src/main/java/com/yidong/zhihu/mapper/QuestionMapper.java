package com.yidong.zhihu.mapper;

/*import com.baomidou.mybatisplus.core.mapper.BaseMapper;*/
import com.yidong.zhihu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper{

    @Insert("insert into question (title,detail,quser_id,quser_name) values (#{title},#{detail},#{quser_id},#{quser_name})")
    int add(Question question);

    @Select("select * from question")
    List<Question> selectPage();

    // 分页查询 我的提问 总记录数
    @Select("select count(*) from question where quser_name = #{quser_name}")
    int findTotalCount(String quser_name);

    // 分页查询 我的提问 每页记录
    @Select("select * from question where quser_name = #{quser_name}  limit #{start},#{rows}")
    List<Question> findByPage(int start, int rows, String quser_name);

    // 根据标题查找对应的question实体
    @Select("select * from question where title = #{title}")
    String FindQuestionByTitle(String title);
}
