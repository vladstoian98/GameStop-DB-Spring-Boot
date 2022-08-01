package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.service.GameConsoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/gameconsoles")
public class GameConsoleController {

    private GameConsoleService gameConsoleService;

    public GameConsoleController(GameConsoleService gameConsoleService) {
        this.gameConsoleService = gameConsoleService;
    }

    @PostMapping
    public ResponseEntity<GameConsole> create(
        @RequestBody GameConsole gameConsole) {

        GameConsole createdGameCosnole = gameConsoleService.create(gameConsole);

        return ResponseEntity
                .created(URI.create("/gameconsoles" + ""))
                .body(createdGameCosnole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameConsole> findGameConsoleById(
            @PathVariable Long id) {

        GameConsole selectedGameConsole = gameConsoleService.findGameConsoleById(id);

        return ResponseEntity
                .created(URI.create("/gameconsoles" + "/id"))
                .body(selectedGameConsole);
    }
}
