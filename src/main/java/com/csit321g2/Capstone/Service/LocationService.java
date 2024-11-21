package com.csit321g2.Capstone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.LocationEntity;
import com.csit321g2.Capstone.Repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    LocationRepository lrepo;

    public List<LocationEntity> getAllLocations() {
        return lrepo.findAll();
    }
    
}
