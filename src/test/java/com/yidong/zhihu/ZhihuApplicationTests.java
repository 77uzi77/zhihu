package com.yidong.zhihu;

import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ZhihuApplicationTests {

    @Autowired
    private QuestionMapper questionMapper;

   /* @Test
    void contextLoads() {
        //参数是一个Wrapper，条件构造器,这里我们先不用，则设为null
        //查询全部用户
        List<Question> questions = questionMapper.selectList(null);
        questions.forEach(System.out::println);
    }*/

    @Test
    void testLog(){
        log.info("haoba.........................................................");
    }

}
