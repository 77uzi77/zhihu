package com.yidong.zhihu.service;


import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.ResultBean;

public interface AnswerService {
    boolean answerQuestion(Answer answer);

    ResultBean<?> findAnswer(int pageNum, int pageSize, String aquestion_id);

    ResultBean<?> selectMyAnsByPage(int pageNum, int pageSize, int auser_id);

    int countMyAns(int auser_id);
}
