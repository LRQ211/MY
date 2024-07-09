package com.demo.javaBean;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Detail {
    private int mqttId;
    private int movieId;
    private String temp;
    private String humidity;
    private String alarm;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    private String light;
    private String place;

    public int getMqttId() {
        return mqttId;
    }

    public void setMqttId(int mqttId) {
        this.mqttId = mqttId;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}