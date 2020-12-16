package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.QuestionService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  处理提问相关请求
 */
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * @param question
     * @return ResultBean<String>
     * @author lzc
     * @date 2020/12/10
     *  提问
     */
    @PostMapping("askQuestion")
    public ResultBean<String> askQuestion(@RequestBody Question question){
//        String token = request.getHeader("token");
//        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
//        String username = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("username").asString());
//        System.out.println(username);
//        System.out.println(id);
//        question.setQuser_id(Integer.parseInt(id));
//        System.out.println(question.getId());
//        question.setQuser_name(username);
        if (questionService.askQuestion(question)){
            return new ResultBean<>("提问成功！！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("提问失败！！问题为空或者问题已存在！",ResultBean.SUCCESS_CODE);
        }
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     *  分页查询所有问题
     */
    @GetMapping("findPage")
    public ResultBean<?> findPage(int pageNum, int pageSize){
        return new ResultBean<>(questionService.findPage(pageNum,pageSize));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param username
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     *  分页查询我的提问
     */
    @GetMapping("findMyQueByPage")
    public ResultBean<?> findMyQueByPage(int pageNum, int pageSize,String username){
        return new ResultBean<>(questionService.findMyQueByPage(pageNum, pageSize,username));
    }

    /**
     * @param id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     *  根据id查找问题
     */
    @GetMapping("findQuestionById")
    public ResultBean<?> findQuestionByTitle( int id ){
        return new ResultBean<>(questionService.findQuestionById(id));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param content
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10
     * 模糊查找问题
     */
    @GetMapping("findQuestion")
    public ResultBean<?> findQuestion(int pageNum, int pageSize,String content){
        return new ResultBean<>(questionService.findQuestion(pageNum,pageSize,content));
    }

    /**
     * @param quser_name
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     *  查找我的提问数量
     */
    @GetMapping("countMyQues")
    public ResultBean<?> countMyQues(String quser_name){
        return new ResultBean<>(questionService.countMyQues(quser_name));
    }
}
