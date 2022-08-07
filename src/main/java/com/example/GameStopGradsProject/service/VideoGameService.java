package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class VideoGameService {

    private GameCharacterRepository gameCharacterRepository;

    private VideoGameRepository videoGameRepository;

    private GameStopShopRepository gameStopShopRepository;

    public VideoGameService(GameCharacterRepository gameCharacterRepository, VideoGameRepository videoGameRepository, GameStopShopRepository gameStopShopRepository) {
        this.gameCharacterRepository = gameCharacterRepository;
        this.videoGameRepository = videoGameRepository;
        this.gameStopShopRepository = gameStopShopRepository;
    }

    @Transactional
    public VideoGame create(VideoGame videoGame) {

        videoGame.getGameCharacters().forEach(gameCharacter -> {
            ArrayList<VideoGame> videoGames = new ArrayList<>();
            videoGames.add(videoGame);
            gameCharacter.setVideoGames(videoGames);
        });

        videoGame.getGameStopShops().forEach(gameStopShop -> {
            ArrayList<VideoGame> videoGames = new ArrayList<>();
            videoGames.add(videoGame);
            gameStopShop.setVideoGames(videoGames);
        });

        gameCharacterRepository.saveAll(videoGame.getGameCharacters());
        gameStopShopRepository.saveAll(videoGame.getGameStopShops());
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
}
