package com.keyin.finalsprint.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.finalsprint.Entity.LogEntity;
import com.keyin.finalsprint.Repository.LogRepository;

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

}
