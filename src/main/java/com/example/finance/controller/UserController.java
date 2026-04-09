package com.example.finance.controller;

import com.example.finance.entity.User;
import com.example.finance.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 新增 User
    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 查全部
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }
}