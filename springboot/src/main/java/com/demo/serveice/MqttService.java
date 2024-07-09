package com.demo.serveice;

import com.demo.javaBean.Mqtt;


import java.util.List;

public interface MqttService {
    List<Mqtt> selectMqtt();
    List<Mqtt>   selectMqttByMovieId(String movieid);

}