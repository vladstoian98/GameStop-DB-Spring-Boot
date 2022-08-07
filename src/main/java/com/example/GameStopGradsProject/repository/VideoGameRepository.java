package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

    Optional<VideoGame> findVideoGameById(Long id);

}
