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

    /**
     * 分页查询
     */
    @Override
    public ResultBean<?> findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = questionMapper.selectPage();
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return new ResultBean<>(pageList.getList());
    }

    /**
     * 模糊查询
     */
    @Override
    public ResultBean<?> findQuestion(int pageNum, int pageSize,String content) {
        PageHelper.startPage(pageNum, pageSize);
        // 拼接模糊查询
        content = "%" + content + "%";
        List<Question> questionList = questionMapper.selectPageBySearch(content);
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return new ResultBean<>(pageList.getList());
    }
}
