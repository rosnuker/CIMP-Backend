package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.LocationEntity;
import com.csit321g2.Capstone.Service.LocationService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173", "http://10.241.126.247:5173", "http://10.241.242.242:5173/"})
public class LocationController {

    @Autowired
    LocationService lserv;

    @GetMapping("/getAllLocations")
    public List<LocationEntity> getAllLocations() {
        return lserv.getAllLocations();
    }
    
}
