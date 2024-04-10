package com.keyin.finalsprint.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keyin.finalsprint.Entity.LogEntity;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Long> {
    public List<LogEntity> findAll();
}

