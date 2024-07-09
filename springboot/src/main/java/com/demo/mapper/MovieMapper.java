package com.demo.mapper;

import com.demo.javaBean.Movie;

import java.util.List;

public interface MovieMapper {
    List<Movie> selectAll();
}
