//package com.example.GameStopGradsProject.mapper;
//
//import com.example.GameStopGradsProject.dto.VideoGameRequest;
//import com.example.GameStopGradsProject.model.GameCharacter;
//import com.example.GameStopGradsProject.model.VideoGame;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class VideoGameMapper {
//
//    public VideoGame videoGameRequestToVideoGame(VideoGameRequest videoGameRequest) {
//
//        return new VideoGame(videoGameRequest.getName(),
//                videoGameRequest.getPrice(),
//                videoGameRequest.getGameGenre(),
//                videoGameRequest.getGameCharacterRequests().stream()
//                        .map(gameCharacterRequest -> new GameCharacter(gameCharacterRequest.getName(),
//                                gameCharacterRequest.getLevel(),
//                                gameCharacterRequest.getAge(),
//                                gameCharacterRequest.getClassType(),
//                                gameCharacterRequest.getRaceType()))
//                        .collect(Collectors.toList()));
//    }
//}
