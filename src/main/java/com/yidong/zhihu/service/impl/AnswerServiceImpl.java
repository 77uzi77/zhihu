package com.yidong.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.mapper.AnswerMapper;
import com.yidong.zhihu.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 回答问题
     */
    @Override
    public boolean answerQuestion(Answer answer) {

        if (answer.getContent() == null || answer.getAquestion_id() == null){
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

    /**
     * 分页 查询 该问题下 的回答
     */
    @Override
    public ResultBean<?> findAnswer(int pageNum, int pageSize, String aquestion_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> answerList = answerMapper.selectPageByQuestion(aquestion_id);
        PageInfo<Answer> pageList = new PageInfo<>(answerList);
        return new ResultBean<>(pageList.getList());
    }

    /**
     * 分页查询 我的 回答
     */
    @Override
    public ResultBean<?> selectMyAnsByPage(int pageNum, int pageSize, int auser_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> myAnsList = answerMapper.selectMyAnsByPage(auser_id);
        PageInfo<Answer> pageList = new PageInfo<>(myAnsList);
        return new ResultBean<>(pageList.getList());
    }
}
