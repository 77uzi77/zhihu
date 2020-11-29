package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.AnswerService;
import com.yidong.zhihu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

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

        @GetMapping("findMyAns")
        public ResultBean<?> findMyAns(int pageNum, int pageSize,int auser_id) {
            return answerService.selectMyAnsByPage(pageNum, pageSize, auser_id);
        }
}
