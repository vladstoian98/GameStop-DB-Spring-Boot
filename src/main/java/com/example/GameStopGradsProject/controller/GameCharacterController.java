package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.service.GameCharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/gamecharacters")
@AllArgsConstructor
public class GameCharacterController {

    private final GameCharacterService gameCharacterService;

    @PostMapping
    public ResponseEntity<GameCharacter> create(
            @RequestBody GameCharacter gameCharacter) {

            GameCharacter createdGameCharacter = gameCharacterService.create(gameCharacter);

            return ResponseEntity
                    .created(URI.create("/gamecharacters" + ""))
                    .body(createdGameCharacter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<GameCharacter>> findGameCharacterById(
            @PathVariable Long id) {
        try {
            Optional<GameCharacter> returnedGameCharacter = gameCharacterService.findGameCharacterById(id);

            return ResponseEntity
                    .created(URI.create("/gamecharacters" + "/id"))
                    .body(returnedGameCharacter);
        } catch(IdDoesNotExist x) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteGameCharacterById(@PathVariable Long id) {
        try {
            gameCharacterService.deleteGameCharacterById(id);

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
