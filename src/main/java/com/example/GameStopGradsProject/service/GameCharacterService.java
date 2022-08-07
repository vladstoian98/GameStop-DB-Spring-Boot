package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameCharacterService {

    private GameCharacterRepository gameCharacterRepository;

    private VideoGameRepository videoGameRepository;

    public GameCharacterService(GameCharacterRepository gameCharacterRepository, VideoGameRepository videoGameRepository) {
        this.gameCharacterRepository = gameCharacterRepository;
        this.videoGameRepository = videoGameRepository;
    }

    @PreAuthorize("hasAuthority('read') && #user == authentication.name")
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
    public Optional<GameCharacter> findGameCharacterById(Long id) {

        var foundGameCharacter = gameCharacterRepository.findGameCharacterById(id);

        if(foundGameCharacter.isEmpty())
            throw new IdDoesNotExist("GameCharacter", id);
        else
            return foundGameCharacter;
    }
}
