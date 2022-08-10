package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);

    void deleteUserById(Long id);
}
