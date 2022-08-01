package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.service.GameStopShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/gamestopshops")
public class GameStopShopController {

    private GameStopShopService gameStopShopService;

    public GameStopShopController(GameStopShopService gameStopShopService) {
        this.gameStopShopService = gameStopShopService;
    }

    @PostMapping
    public ResponseEntity<GameStopShop> create (
            @RequestBody GameStopShop gameStopShop
    ) {

        GameStopShop createdGameStopShop = gameStopShopService.create(gameStopShop);

        return ResponseEntity
                .created(URI.create("/gamestopshops" + ""))
                .body(createdGameStopShop);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameStopShop> findGameStopShopById (
            @PathVariable Long id) {

        GameStopShop selectedGameStopShop = gameStopShopService.findGameStopShopById(id);

        return ResponseEntity
                .created(URI.create("/gamestopshops" + "/id"))
                .body(selectedGameStopShop);
    }
}