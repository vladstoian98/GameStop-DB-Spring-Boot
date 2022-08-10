package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameCharacterService {

    private final GameCharacterRepository gameCharacterRepository;

    @Transactional
    public GameCharacter create(GameCharacter gameCharacter) {
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

    @Transactional
    public void deleteGameCharacterById(Long id) {
        var selectedGameCharacter = gameCharacterRepository.findGameCharacterById(id);

        if(selectedGameCharacter.isEmpty())
            throw new IdDoesNotExist("Game Character", id);
        else {
            gameCharacterRepository.deleteGameCharacterById(id);
        }
    }
}
