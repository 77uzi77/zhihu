package com.yidong.zhihu.service;

public interface MailService {
    /**
     * @param to 发送邮箱
     * @param subject   题目
     * @param content   内容
     */
    boolean sendMail(String to,String subject,String content);
}
