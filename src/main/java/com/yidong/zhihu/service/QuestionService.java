package com.yidong.zhihu.service;

/*import com.baomidou.mybatisplus.extension.plugins.pagination.Page;*/
import com.yidong.zhihu.entity.PageBean;
import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.entity.ResultBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionService {
    boolean askQuestion(Question question);

    ResultBean<?> findPage(int pageNum,int pageSize);

    /**
     * 分页查询我的提问
     */
/*    Page<Question> findMyQueByPage(int page, int pageSize,String factor);*/
      PageBean<Question> findMyQueByPage( String currentPage, String pageSize,String username);

    /**
     * 根据标题查找对应的question实体
     */
    String FindQuestionByTitle(String title);
}
