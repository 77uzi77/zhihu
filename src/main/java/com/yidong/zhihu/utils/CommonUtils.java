package com.yidong.zhihu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    /**
     * 方法功能：判断一个字符串是否由字母和数字组成
     */
    public static boolean isAlphaNumeric(String s){
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 方法功能：得到当前时间
     */
    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static void main(String[] args) {
        getNowTime();
    }
}
