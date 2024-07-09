package com.demo.javaBean;

public class Movie {
    private String Id;
    private String temperature;
    private String humi;
    private String illumination;
    private String pictureUrl;

    public String  getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }


    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getIllumination() {
        return illumination;
    }

    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
