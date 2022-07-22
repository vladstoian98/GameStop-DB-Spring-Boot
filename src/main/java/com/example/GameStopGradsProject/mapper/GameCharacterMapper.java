package com.example.GameStopGradsProject.mapper;


import com.example.GameStopGradsProject.dto.GameCharacterRequest;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;

public class GameCharacterMapper {

    public GameCharacter gameCharacterRequestToGameCharacter(GameCharacterRequest gameCharacterRequest) {

        return new GameCharacter(gameCharacterRequest.getName(),
                gameCharacterRequest.getLevel(),
                gameCharacterRequest.getAge(),
                gameCharacterRequest.getClassType(),
                gameCharacterRequest.getRaceType(),
                gameCharacterRequest.)

    }

}
