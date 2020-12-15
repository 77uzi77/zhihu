package com.yidong.zhihu.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidong.zhihu.exception.bizException.BizException;
import com.yidong.zhihu.utils.JWTUtils;
import com.yidong.zhihu.utils.RedisUtil;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author ly
 * discription 统一拦截 校验token
 */
/*@Aspect     //作用：把当前类标识为一个切面供容器读取
@Component  //把当前类交给spring管理*/
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求头中令牌
        Map<String,Object> map = new HashMap<>();

        String token = request.getHeader("token");

        try {
            String tokenFromRedis = (String) redisUtil.get(token);
            if (tokenFromRedis != null) {
                JWTUtils.verify(tokenFromRedis);   //验证令牌
                return true;                       //放行请求
            }else {
        //        System.out.println("校验失败，返回登录");
        //        map.put("msg","校验失败，返回登录");
                throw new BizException("校验失败，返回登录");
            }

        }catch (SignatureVerificationException e) {
            e.printStackTrace();
//            map.put("msg","无效签名");
            throw new BizException(e.getMessage());

        }/*catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
              map.put("msg","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效");
        }*/


        /*map.put("state",false);       //设置状态
        //将map转为json  jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;*/

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
