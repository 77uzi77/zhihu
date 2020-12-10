package com.yidong.zhihu.controller;

import com.yidong.zhihu.entity.ResultBean;
import com.yidong.zhihu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理文件相关请求
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    /*
     * @param username
     * @param file
     * @return ResultBean<String>
     * @author ly
     * @date 2020/12/10
     *  上传头像
     */
    @PostMapping("/uploadUserPhoto")
//    @ResponseBody
    public ResultBean<String> uploadUserPhoto(String username , @RequestParam("file") MultipartFile file){
        return new ResultBean<>(fileService.insertUserPhoto(username,file));
    }

    /*
     * @param username
     * @param file
     * @return ResultBean<String>
     * @author ly
     * @date 2020/12/10
     *  上传背景图
     */
    @PostMapping("/uploadBackgroundPhoto")
//    @ResponseBody
    public ResultBean<String> uploadBackgroundPhoto(String username , @RequestParam("file") MultipartFile file){
        return new ResultBean<>(fileService.insertBackgroundPhoto(username,file));
    }
}
