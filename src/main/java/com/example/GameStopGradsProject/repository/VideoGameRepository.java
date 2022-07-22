package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {



}
