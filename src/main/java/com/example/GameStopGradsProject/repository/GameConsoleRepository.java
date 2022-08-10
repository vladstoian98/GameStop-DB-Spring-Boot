package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameConsoleRepository extends JpaRepository<GameConsole, Long> {

    Optional<GameConsole> findGameConsoleById(Long id);

    void deleteGameConsoleById(Long id);
}
