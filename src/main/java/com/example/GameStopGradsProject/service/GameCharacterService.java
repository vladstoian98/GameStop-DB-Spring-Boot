package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameCharacterService {

    private GameCharacterRepository gameCharacterRepository;

    private VideoGameRepository videoGameRepository;

    public GameCharacterService(GameCharacterRepository gameCharacterRepository, VideoGameRepository videoGameRepository) {
        this.gameCharacterRepository = gameCharacterRepository;
        this.videoGameRepository = videoGameRepository;
    }

    @Transactional
    public GameCharacter create(GameCharacter gameCharacter) {

        gameCharacter.getVideoGames().forEach(videoGame -> {
            ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
            gameCharacters.add(gameCharacter);
            videoGame.setGameCharacters(gameCharacters);
        });

        videoGameRepository.saveAll(gameCharacter.getVideoGames());
        return gameCharacterRepository.save(gameCharacter);
    }

    @Transactional
    public GameCharacter findGameCharacterById(Long id) {

        return gameCharacterRepository.findGameCharacterById(id);
    }
}
