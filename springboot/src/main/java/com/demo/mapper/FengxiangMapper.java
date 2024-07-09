package com.demo.mapper;

import com.demo.javaBean.Fengxiang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FengxiangMapper {

    List<Fengxiang> selectFengxiang();
    void updateFengxiang(@Param("fengxiang") Fengxiang fengxiang);
    void addFengxiang(@Param("fengxiang") Fengxiang fengxiang);
    void deleteFengxiang(@Param("fengxiang") Fengxiang fengxiang);

    List<Fengxiang> searchId(String id);
}
