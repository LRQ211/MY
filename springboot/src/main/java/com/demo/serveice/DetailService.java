package com.demo.serveice;

import com.demo.javaBean.Detail;

import java.util.List;

public  interface DetailService {
    List<Detail> selectDetail();

    List<Detail> selectById();
    void insertMovie(Detail detail);
}