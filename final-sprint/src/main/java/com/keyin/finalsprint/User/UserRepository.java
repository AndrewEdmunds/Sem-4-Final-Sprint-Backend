package com.keyin.finalsprint.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public List<UserEntity> findAll();
    List<UserEntity> findByUsername(String username);
    List<UserEntity> findByEmail(String email);
}

