package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.service.GameCharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
//@Validated
@RequestMapping("/gamecharacters")
public class GameCharacterController {

    private GameCharacterService gameCharacterService;

    public GameCharacterController(GameCharacterService gameCharacterService) {
        this.gameCharacterService = gameCharacterService;
    }

    @PostMapping
    public ResponseEntity<GameCharacter> create(
            //@Valid
            @RequestBody GameCharacter gameCharacter) {

        GameCharacter createdGameCharacter = gameCharacterService.create(gameCharacter);

        return ResponseEntity
                .created(URI.create("/gamecharacters" + ""))
                .body(createdGameCharacter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCharacter> findGameCharacterById(
            @PathVariable Long id) {

        GameCharacter returnedGameCharacter = gameCharacterService.findGameCharacterById(id);

        return ResponseEntity
                .created(URI.create("/gamecharacters" + "/id"))
                .body(returnedGameCharacter);
    }




}
