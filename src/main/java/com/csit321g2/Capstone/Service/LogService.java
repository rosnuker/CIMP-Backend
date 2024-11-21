package com.csit321g2.Capstone.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.LogEntity;
import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Repository.ItemRepository;
import com.csit321g2.Capstone.Repository.LogRepository;
import com.csit321g2.Capstone.Repository.UserRepository;

@Service
public class LogService {
    
    @Autowired
    LogRepository logrepo;

    @Autowired
    UserRepository urepo;

    @Autowired
    ItemRepository irepo;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("KK:mm:ss a");

    public LogEntity addLog(LogEntity log, Long uid, Long iid) {
        LocalDate date = LocalDate.now();
        LocalTime tempTime = LocalTime.now();

        String fTime = tempTime.format(dtf);
        LocalTime time = LocalTime.parse(fTime, dtf);

        Optional<UserEntity> optionalUserEntity = urepo.findById(uid);
        Optional<ItemEntity> optionalItemEntity = irepo.findById(iid);

        if(optionalUserEntity.isPresent()) {
            log.setUser(urepo.findByIdWithoutPassword(uid));
        }
        
        if(optionalItemEntity.isPresent()) {
            log.setItem(optionalItemEntity.get());
        }
        
        log.setDate(date);
        log.setTime(time);

        return logrepo.save(log);
    }

    public List<LogEntity> getAllLogs() {
        return logrepo.findAll();
    }
}
