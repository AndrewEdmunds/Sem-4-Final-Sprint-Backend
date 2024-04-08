package com.keyin.finalsprint.Query_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<LogEntity> getAllLogs() {
        return logRepository.findAll();
    }

    // Implement more service methods for CRUD operations
}
