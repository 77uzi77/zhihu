//ok

package com.yidong.zhihu.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    //上传用户头像
    String insertUserPhoto(String username, MultipartFile file);

    //上传背景图
    String insertBackgroundPhoto(String username, MultipartFile file);

}
