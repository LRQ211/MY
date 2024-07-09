package com.demo.servicelmpl;

import com.demo.javaBean.Mqtt;
import com.demo.mapper.MqttMapper;
import com.demo.serveice.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqttServiceImpl implements MqttService {
    @Autowired
    private MqttMapper mqttMapper;
    @Override
    public List<Mqtt> selectMqtt() {
        mqttMapper.selectMqtt();
        List<Mqtt> list=mqttMapper.selectMqtt();
        return list;
    }
    @Override
    public List<Mqtt> selectMqttByMovieId(String movieid){
        List<Mqtt> list = mqttMapper.selectMqttByMovieId(movieid);
        return list;
    }}