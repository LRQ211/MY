package com.demo.controller;

import com.demo.javaBean.Fengxiang;
import com.demo.serveice.FengxiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FengxiangController {

    @Autowired
    private FengxiangService fengxiangService;

    @RequestMapping("/selectFengxiang")
    @ResponseBody
    public List<Fengxiang> selectFengxiang(){
        List<Fengxiang> list=fengxiangService.selectFengxiang();
        return list;
    }
    @RequestMapping("/updateFengxiang")
    @ResponseBody//响应体注解
    public void updateFengxiang(Fengxiang fengxiang){
        fengxiangService.updateFengxiang(fengxiang);
    }

    @RequestMapping("/addFengxiang")
    @ResponseBody//响应体注解
    public void addFengxiang(Fengxiang fengxiang){
        fengxiangService.addFengxiang(fengxiang);
    }
    @RequestMapping("/deleteFengxiang")
    @ResponseBody//响应体注解
    public void deleteFengxiang(Fengxiang fengxiang){
        fengxiangService.deleteFengxiang(fengxiang);
    }

    @RequestMapping("/searchId")
    @ResponseBody
    public List<Fengxiang> searchId(String id){
        List<Fengxiang> list=fengxiangService.searchId(id);
        return list;
    }
}
