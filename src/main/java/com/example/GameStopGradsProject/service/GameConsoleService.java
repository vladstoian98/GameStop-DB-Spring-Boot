package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameConsoleService {

    private final GameConsoleRepository gameConsoleRepository;

    @Transactional
    public GameConsole create(GameConsole gameConsole) {
        return gameConsoleRepository.save(gameConsole);
    }

    @Transactional
    public Optional<GameConsole> findGameConsoleById(Long id) {
        var foundGameConsole = gameConsoleRepository.findGameConsoleById(id);

        if (foundGameConsole.isEmpty())
            throw new IdDoesNotExist("GameConsole", id);
        else
            return foundGameConsole;
    }

    @Transactional
    public void deleteGameConsoleById(Long id) {
        var selectedGameConsole = gameConsoleRepository.findGameConsoleById(id);

        if (selectedGameConsole.isEmpty())
            throw new IdDoesNotExist("Game Console", id);
        else {
            gameConsoleRepository.deleteGameConsoleById(id);
        }
    }
}



