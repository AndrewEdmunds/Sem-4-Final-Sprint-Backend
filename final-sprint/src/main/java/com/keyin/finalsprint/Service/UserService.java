package com.keyin.finalsprint.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public boolean isUsernameUnique(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return !user.isPresent();
    }
    
    public boolean isEmailUnique(String email) {
        List<UserEntity> users = userRepository.findByEmail(email);
        return users.isEmpty();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
