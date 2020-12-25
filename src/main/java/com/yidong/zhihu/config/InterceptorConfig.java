package com.yidong.zhihu.config;

import com.yidong.zhihu.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;
/**
 * @author ly
 * discription 拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public JWTInterceptor getJWTInterceptor(){
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJWTInterceptor())   //拦截器配置
                //.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/sendCode","/user/register","/user/findPassword",
                        "/**/*.js","/**/*.css","/**/*.html","/**/*.jpg","/**/*.png","/**/*.ico",
                        "/static/**","/img/**");
    }

}
