package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class VideoGameService {

    private GameCharacterRepository gameCharacterRepository;

    private VideoGameRepository videoGameRepository;

    public VideoGameService(GameCharacterRepository gameCharacterRepository, VideoGameRepository videoGameRepository) {
        this.gameCharacterRepository = gameCharacterRepository;
        this.videoGameRepository = videoGameRepository;
    }

    @Transactional
    public VideoGame create(VideoGame videoGame) {

        videoGame.getGameCharacters().forEach(gameCharacter -> {
            ArrayList<VideoGame> videoGames = new ArrayList<>();
            videoGames.add(videoGame);
            gameCharacter.setVideoGames(videoGames);
        });

        gameCharacterRepository.saveAll(videoGame.getGameCharacters());
        return videoGameRepository.save(videoGame);
    }
}
