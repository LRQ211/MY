package com.demo.mapper;

import com.demo.javaBean.Detail;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface DetailMapper {

        List<Detail> selectDetail();

        List<Detail> selectById();
        void insertMovie(Detail detail);
    }