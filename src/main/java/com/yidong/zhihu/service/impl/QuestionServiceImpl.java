package com.yidong.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.mapper.QuestionMapper;
import com.yidong.zhihu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public boolean askQuestion(Question question) {
        return question.getTitle() != null && questionMapper.add(question) == 1;
    }

    @Override
    public ResultBean<?> findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = questionMapper.selectPage();
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return new ResultBean<>(pageList.getList());
    }
}
