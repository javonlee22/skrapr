package com.technoformers.skrapr.service;

import com.technoformers.skrapr.entity.User;
import com.technoformers.skrapr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) { return userRepository.findByUsername(username); }

    public User getUserByEmail(String email) { return userRepository.findByEmail(email); }

    public List<User> getAllUsers() { return userRepository.findAll(); }
}
