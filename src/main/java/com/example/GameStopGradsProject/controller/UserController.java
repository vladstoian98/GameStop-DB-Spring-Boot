package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    public UserService userService;

    @PostMapping
    public ResponseEntity<User> create(
            @RequestBody User user) {
        User createdUser = userService.create(user);

        return ResponseEntity
                .created(URI.create("/users" + ""))
                .body(createdUser);
    }

    @PostMapping("/{userId}/{roleId}")
    public void assignBothWays(
            @PathVariable long userId,
            @PathVariable long roleId) {
        userService.assignBothWays(userId, roleId);
    }

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);

            return ResponseEntity
                    .ok()
                    .build();
        }
        catch (IdDoesNotExist e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
