package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class GameStopShopService {

    private GameStopShopRepository gameStopShopRepository;

    private GameConsoleRepository gameConsoleRepository;

    private VideoGameRepository videoGameRepository;

    private EmployeeRepository employeeRepository;

    public GameStopShopService(GameStopShopRepository gameStopShopRepository, GameConsoleRepository gameConsoleRepository, VideoGameRepository videoGameRepository, EmployeeRepository employeeRepository) {
        this.gameStopShopRepository = gameStopShopRepository;
        this.gameConsoleRepository = gameConsoleRepository;
        this.videoGameRepository = videoGameRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public GameStopShop create(GameStopShop gameStopShop) {

        gameStopShop.getGameConsoles().forEach(gameConsole -> {
            ArrayList<GameStopShop> gameStopShops = new ArrayList<>();
            gameStopShops.add(gameStopShop);
            gameConsole.setGameStopShops(gameStopShops);
        });

        gameStopShop.getVideoGames().forEach(videoGame -> {
            ArrayList<GameStopShop> gameStopShops = new ArrayList<>();
            gameStopShops.add(gameStopShop);
            videoGame.setGameStopShops(gameStopShops);
        });

        gameStopShop.getEmployees().forEach(employee -> {
            employee.setGameStopShop(gameStopShop);
        });

        gameConsoleRepository.saveAll(gameStopShop.getGameConsoles());
        videoGameRepository.saveAll(gameStopShop.getVideoGames());
        employeeRepository.saveAll(gameStopShop.getEmployees());
        return gameStopShopRepository.save(gameStopShop);
    }

    @Transactional
    public GameStopShop findGameStopShopById(long id) {

        return gameStopShopRepository.findGameStopShopById(id);
    }

}
