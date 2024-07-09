package com.demo.serveice;

import com.demo.javaBean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int login(@Param("user") User user);

    User getUserDetails(String username);

    //添加
    List<User> selectUser();
    String selectQuanxianByUsername(String username);
    void updateUser(User user);
    void deleteUser(User user);
    void saveUser(User user);
    String getUserPicture(String username);
    void addUser(User user);

    List<User> searchUser(String id);
}
