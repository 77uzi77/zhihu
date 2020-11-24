package com.yidong.zhihu;

import com.yidong.zhihu.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZhihuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhihuApplication.class, args);
    }

}
