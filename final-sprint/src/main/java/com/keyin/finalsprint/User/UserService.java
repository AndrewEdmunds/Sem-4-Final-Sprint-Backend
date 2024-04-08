package com.keyin.finalsprint.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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
        List<UserEntity> users = userRepository.findByUsername(username);
        return users.isEmpty();
    }
    
    public boolean isEmailUnique(String email) {
        List<UserEntity> users = userRepository.findByEmail(email);
        return users.isEmpty();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Implement more service methods for CRUD operations
}
