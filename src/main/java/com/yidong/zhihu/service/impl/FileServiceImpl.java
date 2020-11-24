package com.yidong.zhihu.service.impl;

import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.exception.bizException.BizException;
import com.yidong.zhihu.mapper.UserMapper;
import com.yidong.zhihu.service.FileService;
import com.yidong.zhihu.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private UserMapper userMapper;

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.url}")
    private String url;

    //上传用户头像
    @Override
    public String insertUserPhoto(String username, MultipartFile file) {
        if(file == null){
            throw  new BizException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        log.info("开始上传头像{}",fileName);
        //判断是否为图片格式
        if(!FileUtils.judgeFileType(fileName.substring(fileName.lastIndexOf(".")+1))){
            log.error("文件不是图片格式，不能作为头像");
            throw new BizException("文件格式错误");
        }
        String filePath=path+"user/";
        String fileNewName = FileUtils.getFileNewName(file.getOriginalFilename());

        //调用mapper层保存上传的头像路径名称fileNewName
        userMapper.saveIconpath(username,fileNewName);

        try {
            FileUtils.upload(file.getBytes(),filePath,fileNewName);
        } catch (Exception e) {
            log.error("文件上传失败，未知错误");
            throw new BizException("文件上传未知错误");
        }
        log.info("上传头像成功");
        return fileNewName;
    }
}