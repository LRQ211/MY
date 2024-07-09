package com.demo.servicelmpl;

import com.demo.javaBean.User;
import com.demo.mapper.UserMapper;
import com.demo.serveice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int login(User user) {
        int i = userMapper.login(user);
        return i;
    }

    @Override
    public User getUserDetails(String username) {
        return null;
    }

    //添加
    @Override
    public List<User> selectUser() {
        List<User> list=userMapper.selectUser();
        return list;
    }

    @Override
    public String selectQuanxianByUsername(String username) {
        String quanxian=userMapper.selectQuanxianByUsername(username);
        return quanxian;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public String getUserPicture(String username) {
        String pictureUrl=userMapper.getUserPicture(username);
        return pictureUrl;
    }
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public List<User> searchUser(String id) {
        List<User> list=userMapper.searchUser(id);
        return list;
    }

}
