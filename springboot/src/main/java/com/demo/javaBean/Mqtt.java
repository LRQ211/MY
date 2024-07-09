package com.demo.javaBean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Mqtt {
    private int movieId;
    private int MqttId;
    private String temp;
    private String humidity;
    private String light;
    private int alarm;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMqttId() {
        return MqttId;
    }

    public void setMqttId(int mqttId) {
        MqttId = mqttId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}