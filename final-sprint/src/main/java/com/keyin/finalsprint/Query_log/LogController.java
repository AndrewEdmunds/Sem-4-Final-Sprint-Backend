package com.keyin.finalsprint.Query_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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