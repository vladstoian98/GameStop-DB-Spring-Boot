package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {

    Optional<GameCharacter> findGameCharacterById(Long id);

}
