package com.yidong.zhihu.mapper;

import com.yidong.zhihu.entity.Answer;
import com.yidong.zhihu.entity.User;
import com.yidong.zhihu.entity.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     *  通过邮箱查找用户
     */
    @Select("select * from user where email = #{email}")
    User findUserByEmail(String email);

    /**
     *  查找 用户名 查找用户
     */
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    /**
     *  新增用户
     */
    @Insert("insert into user (username,email,password,message) values (#{username},#{email},#{password},#{message})")
    int saveUser(UserVo user);

    /**
     *  保存头像路径
     */
    @Insert("update user set iconpath=#{iconpath} where username = #{username}")
    void saveIconpath(String username, String iconpath);

    //个人主页：分页查询我的关注
    @Select("select * from follow where follower_id = #{follower_id} and followstate = 1")
    List<User> selectMyFosByPage(int follower_id);

    //个人主页：查看个人基本信息
    @Select("select username,email,message from user where username = #{username}")
    User selectSelfMessage(String username);

    //个人主页：编辑个人信息
    @Update("update user set message = #{message} where id = #{id}")
    void editSelfMessage(int id , String message);

    /**
     *  修改密码
     */
    @Update("update user set password = #{password} where email = #{email}")
    int revisePassword(UserVo user);
}
