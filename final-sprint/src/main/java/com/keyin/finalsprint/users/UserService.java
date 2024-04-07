package com.keyin.finalsprint.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service {

    private final Repository Repository;

    @Autowired
    public Service(Repository Repository) {
        this.Repository = Repository;
    }

    public List<Entity> getAlls() {
        return Repository.findAll();
    }

    // Implement more service methods for CRUD operations
}
