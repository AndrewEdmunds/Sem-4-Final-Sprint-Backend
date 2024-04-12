package com.keyin.finalsprint.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keyin.finalsprint.Entity.UserEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public List<UserEntity> findAll();
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByEmail(String email);
}

