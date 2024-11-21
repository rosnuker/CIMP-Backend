package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.LogEntity;
import com.csit321g2.Capstone.Service.LogService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173", "http://10.241.126.247:5173", "http://10.241.242.242:5173/"})
public class LogController {
    
    @Autowired
    LogService logserv;

    @PostMapping("/addLog")
    public LogEntity addLog(@RequestBody LogEntity log, @RequestParam Long uid, @RequestParam Long iid) {
        return logserv.addLog(log, uid, iid);
    } 

    @GetMapping("/getAllLogs")
    public List<LogEntity> getAllLogs() {
        return logserv.getAllLogs();
    }
}
