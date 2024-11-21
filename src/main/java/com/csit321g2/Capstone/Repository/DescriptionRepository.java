package com.csit321g2.Capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.DescriptionEntity;

@Repository
public interface DescriptionRepository  extends JpaRepository<DescriptionEntity, Long> {
    
}
