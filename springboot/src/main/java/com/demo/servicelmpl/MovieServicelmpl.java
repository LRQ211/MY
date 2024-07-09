package com.demo.servicelmpl;

import com.demo.javaBean.Movie;
import com.demo.mapper.MovieMapper;
import com.demo.serveice.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServicelmpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Override
    public List<Movie> selectAll() {
        List<Movie> list=movieMapper.selectAll();
        return list;
    }
}
