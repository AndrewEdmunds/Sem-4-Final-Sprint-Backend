package com.keyin.finalsprint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.keyin.finalsprint.Entity.LogEntity;
import com.keyin.finalsprint.Service.LogService;

import java.util.List;

@RestController
@RequestMapping("/query_logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<LogEntity> getAllLogs() {
        return logService.getAllLogs();
    }

    // Add more CRUD endpoints as needed
}