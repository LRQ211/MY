package com.demo.controller;

import com.demo.javaBean.Detail;
import com.demo.serveice.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class DetailController {
    @Autowired
    private DetailService detailService;

    @RequestMapping("selectDetail")
    @ResponseBody
    public List<Detail> selectDetail() {
        List<Detail> list = detailService.selectDetail();
        return list;
    }
    @RequestMapping("selectById")
    @ResponseBody
    public List<Detail> selectById() {
        List<Detail> list = detailService.selectById();
        return list;
    }
    @RequestMapping("/insertMovie")
    @ResponseBody
    public void insertMovie(@RequestBody Detail detail) {
        detailService.insertMovie(detail);
    }






}
