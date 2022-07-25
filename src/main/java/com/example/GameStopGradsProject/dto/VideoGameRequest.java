package com.example.GameStopGradsProject.dto;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.GameGenre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class VideoGameRequest {

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private GameGenre gameGenre;

    private List<GameCharacterRequest> gameCharacterRequests;

    public VideoGameRequest(String name, int price, GameGenre gameGenre) {
        this.name = name;
        this.price = price;
        this.gameGenre = gameGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GameGenre getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(GameGenre gameGenre) {
        this.gameGenre = gameGenre;
    }

    public List<GameCharacterRequest> getGameCharacterRequests() {
        return gameCharacterRequests;
    }

    public void setGameCharacterRequests(List<GameCharacterRequest> gameCharacterRequests) {
        this.gameCharacterRequests = gameCharacterRequests;
    }
}
