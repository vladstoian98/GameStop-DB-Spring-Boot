package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.service.GameStopShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/gamestopshops")
@AllArgsConstructor
public class GameStopShopController {

    private final GameStopShopService gameStopShopService;

    @PostMapping
    public ResponseEntity<GameStopShop> create (
            @RequestBody GameStopShop gameStopShop) {
            GameStopShop createdGameStopShop = gameStopShopService.create(gameStopShop);

            return ResponseEntity
                    .created(URI.create("/gamestopshops" + ""))
                    .body(createdGameStopShop);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<GameStopShop>> findGameStopShopById (
            @PathVariable Long id) {
        try {
            Optional<GameStopShop> selectedGameStopShop = gameStopShopService.findGameStopShopById(id);

            return ResponseEntity
                    .created(URI.create("/gamestopshops" + "/id"))
                    .body(selectedGameStopShop);
        } catch (IdDoesNotExist x) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteGameStopShopById(@PathVariable Long id) {
        try {
            gameStopShopService.deleteGameStopShopById(id);

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

    @PostMapping("/bothwaysconsole/{gameStopShopId}/{gameConsoleId}")
    public void assignBothWaysForGameConsole(
            @PathVariable long gameStopShopId,
            @PathVariable long gameConsoleId) {
        gameStopShopService.assignBothWaysForGameConsole(gameStopShopId, gameConsoleId);
    }

    @PostMapping("/bothwaysgame/{gameStopShopId}/{videoGameId}")
    public void assignBothWaysForVideoGame(
            @PathVariable long gameStopShopId,
            @PathVariable long videoGameId) {
        gameStopShopService.assignBothWaysForVideoGame(gameStopShopId, videoGameId);
    }
}
