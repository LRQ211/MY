package com.demo.controller;

import com.demo.javaBean.Movie;
import com.demo.serveice.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Movie> selectAll(){
        List<Movie> list=movieService. selectAll();
        return list;
}

}
