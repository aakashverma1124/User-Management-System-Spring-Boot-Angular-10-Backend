package com.usermanagement.user.controller;

import com.usermanagement.user.model.User;
import com.usermanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) { return userRepository.save(user); }


}
