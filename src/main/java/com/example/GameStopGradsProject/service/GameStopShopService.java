package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameStopShopService {

    private final GameStopShopRepository gameStopShopRepository;

    private final GameConsoleRepository gameConsoleRepository;

    private final VideoGameRepository videoGameRepository;

    private final EmployeeRepository employeeRepository;

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

        gameStopShop.getEmployees().forEach(employee -> employee.setGameStopShop(gameStopShop));

        gameConsoleRepository.saveAll(gameStopShop.getGameConsoles());
        videoGameRepository.saveAll(gameStopShop.getVideoGames());
        employeeRepository.saveAll(gameStopShop.getEmployees());
        return gameStopShopRepository.save(gameStopShop);
    }

    @Transactional
    public Optional<GameStopShop> findGameStopShopById(long id) {
        var foundGameStopShop = gameStopShopRepository.findGameStopShopById(id);

        if(foundGameStopShop.isEmpty())
            throw new IdDoesNotExist("GameStopShop", id);
        else
            return foundGameStopShop;
    }

    @Transactional
    public void deleteGameStopShopById(Long id) {
        var selectedGameStopShop = gameStopShopRepository.findGameStopShopById(id);

        if(selectedGameStopShop.isEmpty())
            throw new IdDoesNotExist("Game Stop Shop", id);
        else {
            gameStopShopRepository.deleteGameStopShopById(id);
        }
    }

    @Transactional
    public void assignBothWaysForGameConsole(long gameStopShopId, long gameConsoleId) {
        Optional<GameStopShop> foundGameStopShop = gameStopShopRepository.findGameStopShopById(gameStopShopId);
        Optional<GameConsole> foundGameConsole = gameConsoleRepository.findGameConsoleById(gameConsoleId);

        if(foundGameStopShop.isEmpty())
            throw new IdDoesNotExist("Game Stop Shop", gameStopShopId);
        else if(foundGameConsole.isEmpty())
            throw new IdDoesNotExist("Game Console", gameConsoleId);
        else {
            foundGameStopShop.get().getGameConsoles().add(foundGameConsole.get());
            foundGameConsole.get().getGameStopShops().add(foundGameStopShop.get());
        }
    }

    @Transactional
    public void assignBothWaysForVideoGame(long gameStopShopId, long videoGameId) {
        Optional<GameStopShop> foundGameStopShop = gameStopShopRepository.findGameStopShopById(gameStopShopId);
        Optional<VideoGame> foundVideoGame = videoGameRepository.findVideoGameById(videoGameId);

        if(foundGameStopShop.isEmpty())
            throw new IdDoesNotExist("Game Stop Shop", gameStopShopId);
        else if(foundVideoGame.isEmpty())
            throw new IdDoesNotExist("Video Game", videoGameId);
        else {
            foundGameStopShop.get().getVideoGames().add(foundVideoGame.get());
            foundVideoGame.get().getGameStopShops().add(foundGameStopShop.get());
        }
    }
}
