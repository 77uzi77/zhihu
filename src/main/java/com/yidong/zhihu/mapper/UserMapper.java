package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    User findUserByEmail(String email);

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user (username,email,password,message) values (#{username},#{email},#{password},#{message})")
    int saveUser(UserVo user);

    @Insert("update user set iconpath=#{iconpath} where username = #{username}")
    void saveIconpath(String username, String iconpath);

}
