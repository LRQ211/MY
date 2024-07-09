package com.demo.controller;


import com.demo.javaBean.Mqtt;
import com.demo.serveice.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller

public class MqttController {
    @Autowired
    private MqttService mqttService;

    @RequestMapping("selectMqtt")
    @ResponseBody
    public List<Mqtt> selectMqtt(@RequestParam(value = "movieid", required = false) String movieid) {
        if (movieid == null) {
            List<Mqtt> list =  mqttService.selectMqtt();
            return list;
        } else {
            List<Mqtt> list =  mqttService.selectMqttByMovieId(movieid);
            return list;
        }
    }}