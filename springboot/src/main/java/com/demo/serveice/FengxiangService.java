package com.demo.serveice;

import com.demo.javaBean.Fengxiang;

import java.util.List;

public interface FengxiangService {

    List<Fengxiang> selectFengxiang();
    void updateFengxiang(Fengxiang fengxiang);
    void addFengxiang(Fengxiang fengxiang);
    void deleteFengxiang(Fengxiang fengxiang);
    List<Fengxiang> searchId(String id);
}
