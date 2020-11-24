//ok

package com.yidong.zhihu.service;

import com.yidong.zhihu.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: chenyu
 * @date: 2020/8/5 16:19
 */
public interface FileService {

    //上传用户头像
    String insertUserPhoto(String username, MultipartFile file);

}
