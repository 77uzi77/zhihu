package com.yidong.zhihu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.mapper.QuestionMapper;
import com.yidong.zhihu.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 提问
     * @author lzc
     */
    @Override
    public boolean askQuestion(Question question) {
        if (question.getTitle() != null){
            try {
                int id = questionMapper.findByTitle(question.getTitle());
            }catch (Exception e){
                return questionMapper.add(question) == 1;
            }
            return false;
        }
        return false;
    }

    /**
     * 分页查询
     * @author lzc
     */
    @Override
    public List<Question> findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = questionMapper.selectPage();
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return pageList.getList();
    }

    /**
     * 分页 查询 我的提问
     * @author ly
     */
    @Override
    public List<Question> findMyQueByPage(int pageNum, int pageSize, String quser_name) {
//        //设置总记录数
////        int totalCount = questionMapper.findTotalCount(quser_name);
////        // 得到分页条件参数
////        Object[] pageParams = PageUtil.getPageParams(currentPage,pageSize, totalCount);
////        PageBean pb = (PageBean) pageParams[0];
////        int start = (int) pageParams[1];
////
////        List<Question> list = questionMapper.findByPage(start,pb.getPageSize(),quser_name);
////        pb.setList(list);
////
////        return pb;
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = questionMapper.findMyQuestionByPage(quser_name);
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return pageList.getList();
    }

    /**
     * 根据 id 查找 对应 问题
     * @author ly
     */
    @Override
    public Question findQuestionById(int id) {
        return questionMapper.findQuestionById(id);
    }

    /**
     * 模糊查询
     * @author lzc
     */
    @Override
    public List<Question> findQuestion(int pageNum, int pageSize, String content) {
        PageHelper.startPage(pageNum, pageSize);
        // 拼接模糊查询
        content = "%" + content + "%";
        List<Question> questionList = questionMapper.selectPageBySearch(content);
        PageInfo<Question> pageList = new PageInfo<>(questionList);
        return pageList.getList();
    }

    /**
     * 查找我的提问数量
     * @author ly
     */
    @Override
    public int countMyQues(String quser_name) {
        return questionMapper.countMyQues(quser_name);
    }
}
