package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.mapper.AnswerMapper;
import com.yidong.zhihu.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Override
    public boolean answerQuestion(Answer answer) {

        if (answer.getContent() == null){
            return false;
        }
        /**
         * 查看该用户是否已回答该问题，已回答则修改回答,未回答则新增回答
         */
        try {
            int id = answerMapper.findAnswer(answer.getAuser_id(), answer.getAquestion_id());
            System.out.println(id);
            return answerMapper.reviseAnswer(answer) == 1;
        }catch (Exception e){
            return answerMapper.addAnswer(answer) == 1;
        }
    }
}
