package com.yidong.zhihu.entity.vo;

import lombok.Data;


/**
 * @author lzc
 * discription 作为二级评论
 */
@Data
public class ReplyVo {
    private Integer id;
    private String content;
    private Integer reply_id;
    private String replyName;
    private String comment_time;
    private String reply_icon;

    private Integer replied_id;
    private String replied_name;
    private String replied_icon;

}
