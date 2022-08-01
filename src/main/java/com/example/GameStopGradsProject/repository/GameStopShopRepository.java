package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.GameStopShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStopShopRepository extends JpaRepository<GameStopShop, Long> {

    GameStopShop findGameStopShopById(Long id);


}
