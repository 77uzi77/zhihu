package com.yidong.zhihu.entity.vo;

import com.yidong.zhihu.entity.Answer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzc
 * @date 2020/12/17 16 14
 * discription
 * 回答与前端交互实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerVo extends Answer {
    // 回答者的用户名
    private String username;
    // 回答者的头像
    private String iconPath;
}
