package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(
            @RequestBody User user) {

        User createdUser = userService.create(user);

        return ResponseEntity
                .created(URI.create("/users" + ""))
                .body(createdUser);
    }

    @PostMapping("/{userId}/{roleId}")
    public void assignRole(
            @PathVariable long userId,
            @PathVariable long roleId) {

        userService.assignRole(userId, roleId);
    }
}
