package com.csit321g2.Capstone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.DescriptionEntity;
import com.csit321g2.Capstone.Repository.DescriptionRepository;

@Service
public class DescriptionService {

    @Autowired
    DescriptionRepository drepo;

    public DescriptionEntity insertDescription(DescriptionEntity description) {
        return drepo.save(description);
    }

    public List<DescriptionEntity> getAllDescription() {
        return drepo.findAll();
    }
    
}
