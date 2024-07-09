package com.demo.servicelmpl;

import com.demo.javaBean.Detail;
import com.demo.mapper.DetailMapper;
import com.demo.serveice.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailMapper detailMapper;
    @Override
    public List<Detail> selectDetail() {
        detailMapper.selectDetail();
        List<Detail> list=detailMapper.selectDetail();
        return list;
    }
    @Override
    public List<Detail> selectById() {
        detailMapper.selectById();
        List<Detail> list=detailMapper.selectById();
        return list;
    }

    @Override
    public void insertMovie(Detail detail) {
        detailMapper.insertMovie(detail);
    }
//    public ResultData<Movie> selectAll(SearchBean searchBean){
//        Page<Movie> page=new Page<>(searchBean.getPageNum(),searchBean.getPageSize) )}
//
//    public MovieMapper getMovieMapper() {
//        return movieMapper;
//    }
}