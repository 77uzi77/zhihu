package com.yidong.zhihu.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yidong.zhihu.entity.Question;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.QuestionService;
import com.yidong.zhihu.utils.JWTUtils;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("askQuestion")
    public ResultBean<String> askQuestion(HttpServletRequest request, @RequestBody Question question){
        String token = request.getHeader("token");
        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
        String username = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("username").asString());
        System.out.println(username);
        System.out.println(id);
        question.setQuser_id(Integer.parseInt(id));
//        System.out.println(question.getId());
        question.setQuser_name(username);

        if (questionService.askQuestion(question)){
            return new ResultBean<>("提问成功！！");
        }else{
            return new ResultBean<>("提问失败！！请正确填写问题！");
        }
    }

    /**
     *  分页查询所有问题
     */
    @GetMapping("findPage")
    public ResultBean<?> findPage(int pageNum, int pageSize){
        return questionService.findPage(pageNum,pageSize);
    }

    /**
     * 查询分页数据
     */
    @GetMapping("findMyQueByPage")
    public ResultBean<?> findMyQueByPage(@RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
                                         @RequestParam(name = "pageSize", defaultValue = "3") String pageSize,
                                         @RequestParam(name = "username", defaultValue = "") String username){
        return new ResultBean<>(questionService.findMyQueByPage(currentPage, pageSize,username));
    }

    /**
     * 根据标题查找对应question实体
     */
    @GetMapping("findQuestionByTitle")
    public ResultBean<?> FindQuestionByTitle( String title ){
        return new ResultBean<>(questionService.FindQuestionByTitle(title));
    }

    /**
     *  模糊搜索问题
     */
    @GetMapping("findQuestion")
    public ResultBean<?> findQuestion(int pageNum, int pageSize,String content){
        return questionService.findQuestion(pageNum,pageSize,content);
    }
}
