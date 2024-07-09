package com.demo.controller;

import com.demo.javaBean.User;
import com.demo.serveice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
通过@Autowired注解注入了UserService对象，用于处理用户登录和注册的业务逻辑。
通过@RequestMapping注解指定了处理请求的路径，例如/login和/register。同时通过@ResponseBody注解表示返回结果是一个json格式的数据。
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /*
    在login方法中，接收一个User对象作为参数，该对象包含用户的用户名和密码信息。然后调用userService的login方法进行登录验证，并返回登录结果。
    */

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        int i = userService.login(user);
        System.out.println("-----" + i);
        if (i == 1) {
            // 如果登录成功，返回状态码200和用户信息
            User userDetails = userService.getUserDetails(user.getUsername());
            return ResponseEntity.ok(userDetails);
        } else {
            // 如果登录失败，返回状态码401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }
//    @RequestMapping("/register")
//    public void register(){
//    }
////        @RequestMapping("/login")
////    @ResponseBody//响应体注解
////    public int login(User user){
////        System.out.println(user.getUsername());
////        System.out.println(user.getPassword());
////        int i = userService.login(user);
////        System.out.printf("----"+i);
////        return i;
////    }


//    添加
@RequestMapping("/selectQuanxianByUsername")
@ResponseBody//响应体注解
public String selectQuanxianByUsername(String username){
    String quanxian=userService.selectQuanxianByUsername(username);
    return  quanxian;
}

    @RequestMapping("/updateUser")
    @ResponseBody//响应体注解
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @RequestMapping("/selectUser")
    @ResponseBody
    public List<User> selectUsers(){
        List<User> list=userService.selectUser();
        return list;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody//响应体注解
    public void deleteUser(User user){
        userService.deleteUser(user);
    }

    @RequestMapping("/saveUser")
    @ResponseBody//响 应体注解
    public void saveUser(User user){
        userService.saveUser(user);
    }


    @RequestMapping("/getUserPicture")
    @ResponseBody//响应体注解
    public String getUserPicture(String username){
        String pictureUrl=userService.getUserPicture(username);
        return  pictureUrl;
    }
    @RequestMapping("/addUser")
    @ResponseBody//响应体注解
    public void addUser(User user){
        userService.addUser(user);
    }

    @RequestMapping("/searchUser")
    @ResponseBody
    public List<User> searchUser(String id){
        List<User> list=userService.searchUser(id);
        return list;
    }


}
