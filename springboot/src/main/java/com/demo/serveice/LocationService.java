package com.demo.serveice;

import com.demo.javaBean.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private List<Location> locations = new ArrayList<>();

    public void handleMessage(Location location) {
        locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }
}
