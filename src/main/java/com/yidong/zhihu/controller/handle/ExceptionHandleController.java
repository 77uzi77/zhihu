package com.yidong.zhihu.controller.handle;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.exception.bizException.BizException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestControllerAdvice
public class ExceptionHandleController implements HandlerExceptionResolver {


//    private final MailUtil mailUtil;

//    public ExceptionHandleController(MailUtil mailUtil) {
//        this.mailUtil = mailUtil;
//    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
//        log.error("请求异常" + e.getMessage());
        e.printStackTrace();
//        sendMail(e);
        return null;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultBean<?> nullPointerException(NullPointerException e) {
        e.printStackTrace();
//        log.error("空指针异常，可能是参数传错，可能查出内容为空，请告知程序员哥哥请求内容");
//        sendMail(e);
        return new ResultBean<>(new BizException("空指针异常，可能是参数传错，可能查出内容为空，请告知程序员哥哥请求内容"));
    }

    @ExceptionHandler(BizException.class)
    public ResultBean<?> bizException(BizException e) {
//        log.error(e.getMessage());
//        sendMail(e);
        return new ResultBean<>(e);
    }

    @ExceptionHandler(Throwable.class)
    public ResultBean<?> unknownException(Throwable e) {
        e.printStackTrace();
//        log.error("发生了未知的异常，请告知程序员哥哥前来修复");
//        sendMail(e);
        return new ResultBean<>(new BizException("发生了未知的异常，请告知程序员哥哥前来修复"));
    }
    @ExceptionHandler(
            org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResultBean<?> http405Handler(
            org.springframework.web.HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
//        log.error("服务器并不支持您所使用的请求方法");
//        sendMail(e);
        return new ResultBean<>(new BizException("服务器并不支持您所使用的请求方法"));
    }

    @ExceptionHandler(
            org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public ResultBean<?> http405Handler(
            org.springframework.web.HttpMediaTypeNotSupportedException e) {
        e.printStackTrace();
//        log.error("您的请求体格式不正确");
//        sendMail(e);
        return new ResultBean<>(new BizException("您的请求体格式不正确"));
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResultBean<?> http500Handler(IllegalStateException e) {
        e.printStackTrace();
//        log.error("500");
//        sendMail(e);
        return new ResultBean<>(new BizException("500错误，后台背锅"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultBean<?> missHandler(MissingServletRequestParameterException e) {
        e.printStackTrace();
//        log.error("缺少了必要的请求参数");
//        sendMail(e);
        return new ResultBean<>(new BizException("缺少了必要的请求参数"));
    }

    private StringBuilder getErrorMsg(Throwable e) {
        StringBuilder content = new StringBuilder();
        content.append("异常详情开始").append('\n');
        content.append(getTrace(e)).append('\n');
        content.append("异常详情结束").append('\n');
        return content;
    }

    private String getTrace(Throwable e) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(stream));
        return stream.toString();
    }

//    private void sendMail(Throwable e){
//        mailUtil.sendMail("异常抛出", getErrorMsg(e).toString());
//    }


}
