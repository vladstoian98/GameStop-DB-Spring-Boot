package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.model.GameStopShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameConsoleRepository extends JpaRepository<GameConsole, Long> {

    GameConsole findGameConsoleById(Long id);
}
