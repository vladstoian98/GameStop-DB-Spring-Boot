package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {

    GameCharacter findGameCharacterById(Long id);

}
