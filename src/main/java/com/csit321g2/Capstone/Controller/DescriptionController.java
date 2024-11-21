package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.DescriptionEntity;
import com.csit321g2.Capstone.Service.DescriptionService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173", "http://10.241.126.247:5173", "http://10.241.242.242:5173/"})
public class DescriptionController {

    @Autowired
    DescriptionService dserv;

    @PostMapping("/insertDescription")
    public DescriptionEntity insertDescription(@RequestBody DescriptionEntity description) {
        return dserv.insertDescription(description);
    }

    public List<DescriptionEntity> getAllDescription() {
        return dserv.getAllDescription();
    }
    
}
