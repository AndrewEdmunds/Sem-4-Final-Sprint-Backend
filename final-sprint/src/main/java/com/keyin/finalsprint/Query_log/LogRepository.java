package com.keyin.finalsprint.Query_log;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Long> {
    public List<LogEntity> findAll();
}

