package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.Question;

import java.util.List;

public interface QuestionService {
    boolean askQuestion(Question question);

    List<Question> findPage(int pageNum, int pageSize);

    List<Question> findQuestion(int pageNum, int pageSize, String content);
    /**
     * 分页查询我的提问
     * @return
     */
    List<Question> findMyQueByPage(int page, int pageSize, String username);

    /**
     * 根据id查找对应的question实体
     */
    Question findQuestionById(int id);


    int countMyQues(String quser_name);
}
