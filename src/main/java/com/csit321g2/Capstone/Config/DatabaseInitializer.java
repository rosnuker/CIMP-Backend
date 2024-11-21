package com.csit321g2.Capstone.Config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private UserRepository urepo;

    	static Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());


    @PostConstruct
    public void init() {
        if(urepo.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setFname("CIT-U");
            admin.setLname("Admin");
            admin.setUsername("admin");

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPwd = bcrypt.encode("password");
            admin.setPassword(encryptedPwd);

            admin.setType("admin");
            admin.setDepartment("SECURITY");
            admin.setDesignation("Head Admin");
            admin.setDeleted(false);

            urepo.save(admin);
            logger.info("Admin Created!");
        }
    }
}
