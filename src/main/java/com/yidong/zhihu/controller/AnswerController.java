package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.AnswerService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 *  处理回答相关请求
 */
@RestController
@RequestMapping("answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    /*
     * 回答问题
     * @param answer
     * @return ResultBean<String>
     * @author lzc
     * @date 2020/12/10 14:01
     */
    @PostMapping("answerQuestion")
    public ResultBean<String> answerQuestion(@RequestBody Answer answer){
//        System.out.println(answer);
//        String token = request.getHeader("token");
//        String id = String.valueOf(JWTUtils.getTokenInfo(token).getClaim("id").asString());
//        answer.setAuser_id(Integer.parseInt(id));
        if (answerService.answerQuestion(answer)){
            return new ResultBean<>("回答成功！",ResultBean.SUCCESS_CODE);
        }else{
            return new ResultBean<>("回答失败！",ResultBean.SUCCESS_CODE);
        }
    }

    /*
     * 分页查询问题的回答
     * @param pageNum
     * @param pageSize
     * @param aquestion_id
     * @return ResultBean<?>
     * @author lzc
     * @date 2020/12/10 14:02
     */
    @GetMapping("findQuestionAnswer")
    public ResultBean<?> findQuestionAnswer(int pageNum, int pageSize, int aquestion_id){
        return new ResultBean<>(answerService.findAnswer(pageNum,pageSize,aquestion_id));
    }

    /*
     * 查找个人回答
     * @param pageNum
     * @param pageSize
     * @param auser_id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10 14:05
     */
    @GetMapping("findMyAns")
    public ResultBean<?> findMyAns(int pageNum, int pageSize,int auser_id) {
        return new ResultBean<>(answerService.selectMyAnsByPage(pageNum, pageSize, auser_id));
    }

    /*
     * @param auser_id
     * @return ResultBean<?>
     * @author ly
     * @date 2020/12/10
     * 查找 我的回答 数量
     */
    @GetMapping("countMyAns")
    public ResultBean<?> countMyAns(int auser_id){
        return new ResultBean<>(answerService.countMyAns(auser_id));
    }
}
