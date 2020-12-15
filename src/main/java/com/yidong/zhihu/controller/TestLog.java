package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.Comment;
import com.yidong.zhihu.entity.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestLog {

    @GetMapping("testLog")
    public String testLog(){
        log.info("hoabaaaaaaaaaaaaaaaa..............");
        return "";
    }

}
