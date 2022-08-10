package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoGameService {

    private final GameCharacterRepository gameCharacterRepository;

    private final VideoGameRepository videoGameRepository;

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

    @Transactional
    public Optional<VideoGame> findVideoGameById(Long id) {
        var foundVideoGame = videoGameRepository.findVideoGameById(id);

        if(foundVideoGame.isEmpty())
            throw new IdDoesNotExist("VideoGame", id);
        else
            return foundVideoGame;
    }

    @Transactional
    public void deleteVideoGameById(Long id) {
        var selectedVideoGame = videoGameRepository.findVideoGameById(id);

        if(selectedVideoGame.isEmpty())
            throw new IdDoesNotExist("Video Game", id);
        else {
            videoGameRepository.deleteVideoGameById(id);
        }
    }

    @Transactional
    public void assignBothWays(long videoGameId, long gameCharacterId) {
        Optional<VideoGame> foundVideoGame = videoGameRepository.findVideoGameById(videoGameId);
        Optional<GameCharacter> foundGameCharacter = gameCharacterRepository.findGameCharacterById(gameCharacterId);

        if(foundVideoGame.isEmpty())
            throw new IdDoesNotExist("Video Game", videoGameId);
        else if(foundGameCharacter.isEmpty())
            throw new IdDoesNotExist("Game Character", gameCharacterId);
        else {
            foundVideoGame.get().getGameCharacters().add(foundGameCharacter.get());
            foundGameCharacter.get().getVideoGames().add(foundVideoGame.get());
        }
    }
}
