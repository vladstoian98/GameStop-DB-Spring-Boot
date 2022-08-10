package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.service.GameConsoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/gameconsoles")
@AllArgsConstructor
public class GameConsoleController {

    private final GameConsoleService gameConsoleService;

    @PostMapping
    public ResponseEntity<GameConsole> create(
        @RequestBody GameConsole gameConsole) {
            GameConsole createdGameCosnole = gameConsoleService.create(gameConsole);

            return ResponseEntity
                    .created(URI.create("/gameconsoles" + ""))
                    .body(createdGameCosnole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<GameConsole>> findGameConsoleById(
            @PathVariable Long id) {
        try {
            Optional<GameConsole> selectedGameConsole = gameConsoleService.findGameConsoleById(id);

            return ResponseEntity
                    .created(URI.create("/gameconsoles" + "/id"))
                    .body(selectedGameConsole);
        } catch (IdDoesNotExist x) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteGameConsoleById(@PathVariable Long id) {
        try {
            gameConsoleService.deleteGameConsoleById(id);

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
}
