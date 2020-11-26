package com.usermanagement.user.controller;

import com.usermanagement.user.exception.ResourceNotFoundException;
import com.usermanagement.user.model.User;
import com.usermanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get-user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmailId(user.getEmailId());
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

}
