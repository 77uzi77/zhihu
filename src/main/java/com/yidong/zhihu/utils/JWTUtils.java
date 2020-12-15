package com.yidong.zhihu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.Map;

/**
 * @author ly
 * discription JWT工具类
 */
public class JWTUtils {


    @Autowired
    RedisTemplate redisTemplate;

    private static final String SIG = "IAMHERE";
    /**
     * 生成token (header.payload.sig) 并存入Redis
     * 如果同一用户重复登录，token时效重置
     */
    public static String getToken( Map<String,String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        //创建jwt builder   配合map
       JWTCreator.Builder builder = JWT.create();
       //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())   //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIG));                     //sig
  //      System.out.println(token);
        return token;
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
     return JWT.require(Algorithm.HMAC256(SIG)).build().verify(token);
   }

    /**
     * 获取token信息
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIG)).build().verify(token);
        return verify;
    }

}
