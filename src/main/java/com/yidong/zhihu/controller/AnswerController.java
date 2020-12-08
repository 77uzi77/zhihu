package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.AnswerService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    /**
     * 回答问题
     */
    @PostMapping("answerQuestion")
    public ResultBean<String> answerQuestion(HttpServletRequest request,@RequestBody Answer answer){
        System.out.println(answer);
        String token = request.getHeader("token");
        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
        answer.setAuser_id(Integer.parseInt(id));
        if (answerService.answerQuestion(answer)){
            return new ResultBean<>("回答成功！");
        }else{
            return new ResultBean<>("回答失败！");
        }
    }

    /**
     *  分页查询 我的 回答
     */
    @GetMapping("findMyAns")
    public ResultBean<?> findMyAns(int pageNum, int pageSize,int auser_id) {
        return answerService.selectMyAnsByPage(pageNum, pageSize, auser_id);
    }

    /**
     * 个人主页：计算我的回答总数
     */
    @GetMapping("countMyAns")
    public ResultBean<?> countMyAns(int auser_id){
        return new ResultBean<>(answerService.countMyAns(auser_id));
    }
}
