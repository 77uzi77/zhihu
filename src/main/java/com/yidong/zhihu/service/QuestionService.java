package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.entity.ResultBean;

import java.util.List;

public interface QuestionService {
    boolean askQuestion(Question question);

    ResultBean<?> findPage(int pageNum, int pageSize);

    ResultBean<?> findQuestion(int pageNum, int pageSize,String content);
}
