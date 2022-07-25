package com.example.GameStopGradsProject.mapper;

import com.example.GameStopGradsProject.dto.VideoGameRequest;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoGameMapper {

    public VideoGame videoGameRequestToVideoGame(VideoGameRequest videoGameRequest) {

        GameCharacterMapper gameCharacterMapper = new GameCharacterMapper();

        List<GameCharacter> gameCharacters = new ArrayList<>();
        gameCharacters = videoGameRequest.getGameCharacterRequests().stream()
                .map(gameCharacterRequest -> gameCharacterMapper.gameCharacterRequestToGameCharacter(gameCharacterRequest))
                .collect(Collectors.toList());

        return new VideoGame(videoGameRequest.getName(),
                videoGameRequest.getPrice(),
                videoGameRequest.getGameGenre(),
                gameCharacters);
    }
}
