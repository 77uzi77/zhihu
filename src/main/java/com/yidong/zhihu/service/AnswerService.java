package com.yidong.zhihu.service;


import com.yidong.zhihu.entity.Answer;

import java.util.List;

public interface AnswerService {
    boolean answerQuestion(Answer answer);

    List<Answer> findAnswer(int pageNum, int pageSize, int aquestion_id);

    List<Answer> selectMyAnsByPage(int pageNum, int pageSize, int auser_id);

    int countMyAns(int auser_id);
}
