package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameStopShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStopShopRepository extends JpaRepository<GameStopShop, Long> {

    Optional<GameStopShop> findGameStopShopById(Long id);

    void deleteGameStopShopById(Long id);
}
