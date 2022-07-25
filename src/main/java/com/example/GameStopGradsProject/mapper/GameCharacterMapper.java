package com.example.GameStopGradsProject.mapper;


import com.example.GameStopGradsProject.dto.GameCharacterRequest;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameCharacterMapper {

    public GameCharacter gameCharacterRequestToGameCharacter(GameCharacterRequest gameCharacterRequest) {

        VideoGameMapper videoGameMapper = new VideoGameMapper();

        List<VideoGame> videoGames = new ArrayList<>();
        videoGames = gameCharacterRequest.getVideoGameRequests().stream()
                .map(videoGameRequest -> videoGameMapper.videoGameRequestToVideoGame(videoGameRequest))
                .collect(Collectors.toList());

        return new GameCharacter(gameCharacterRequest.getName(),
                gameCharacterRequest.getLevel(),
                gameCharacterRequest.getAge(),
                gameCharacterRequest.getClassType(),
                gameCharacterRequest.getRaceType(),
                videoGames);
    }
}
