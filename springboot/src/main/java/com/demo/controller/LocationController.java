package com.demo.controller;

import com.demo.javaBean.Location;
import com.demo.serveice.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationService.getLocations();
    }
}
