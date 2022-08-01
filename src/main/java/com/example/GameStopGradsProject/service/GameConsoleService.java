package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class GameConsoleService {

    private GameConsoleRepository gameConsoleRepository;

    private GameStopShopRepository gameStopShopRepository;

    public GameConsoleService(GameConsoleRepository gameConsoleRepository, GameStopShopRepository gameStopShopRepository) {
        this.gameConsoleRepository = gameConsoleRepository;
        this.gameStopShopRepository = gameStopShopRepository;
    }

    @Transactional
    public GameConsole create(GameConsole gameConsole) {

        gameConsole.getGameStopShops().forEach(gameStopShop -> {
            ArrayList<GameConsole> gameConsoles = new ArrayList<>();
            gameConsoles.add(gameConsole);
            gameStopShop.setGameConsoles(gameConsoles);
        });

        gameStopShopRepository.saveAll(gameConsole.getGameStopShops());
        return gameConsoleRepository.save(gameConsole);

    }

    @Transactional
    public GameConsole findGameConsoleById(Long id) {

        return gameConsoleRepository.findGameConsoleById(id);
    }


}

