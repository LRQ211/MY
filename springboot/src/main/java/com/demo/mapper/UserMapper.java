package com.demo.mapper;

import com.demo.javaBean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int login(@Param("user")User user);

    //添加
    List<User> selectUser();
    String selectQuanxianByUsername(String username);
    void updateUser(@Param("user")User user);
    void deleteUser(@Param("user")User user);
    void saveUser(@Param("user")User user);
    String getUserPicture(String username);
    void addUser(@Param("user")User user);
    List<User> searchUser(String id);
}
