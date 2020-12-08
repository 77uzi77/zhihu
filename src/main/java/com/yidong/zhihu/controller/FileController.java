package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;


    //上传头像
    @PostMapping("/uploadUserPhoto")
    @ResponseBody
    public ResultBean<String> uploadUserPhoto(String username , @RequestParam("file") MultipartFile file){
        return new ResultBean<>(fileService.insertUserPhoto(username,file));
    }

    //上传背景图
    @PostMapping("/uploadBackgroundPhoto")
    @ResponseBody
    public ResultBean<String> uploadBackgroundPhoto(String username , @RequestParam("file") MultipartFile file){
        return new ResultBean<>(fileService.insertBackgroundPhoto(username,file));
    }
}
