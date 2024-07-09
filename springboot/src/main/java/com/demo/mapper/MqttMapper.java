package com.demo.mapper;

import com.demo.javaBean.Mqtt;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MqttMapper {
    List<Mqtt> selectMqtt();
    List<Mqtt> selectMqttByMovieId(String movieid);

}