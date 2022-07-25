package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.dto.GameCharacterRequest;
import com.example.GameStopGradsProject.mapper.GameCharacterMapper;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.service.GameCharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/gameCharacters")
public class GameCharacterController {

    private GameCharacterService gameCharacterService;

    private GameCharacterMapper gameCharacterMapper;

    public GameCharacterController(GameCharacterService gameCharacterService, GameCharacterMapper gameCharacterMapper) {
        this.gameCharacterService = gameCharacterService;
        this.gameCharacterMapper = gameCharacterMapper;
    }

    @PostMapping
    public ResponseEntity<GameCharacter> create(
            @Valid
            @RequestBody GameCharacterRequest gameCharacterRequest) {
        GameCharacter gameCharacter = gameCharacterMapper.gameCharacterRequestToGameCharacter(gameCharacterRequest);
        GameCharacter createdGameCharacter = gameCharacterService.create(gameCharacter);

        return ResponseEntity
                .created(URI.create("/gameCharacters" + ""))
                .body(createdGameCharacter);
    }


}
