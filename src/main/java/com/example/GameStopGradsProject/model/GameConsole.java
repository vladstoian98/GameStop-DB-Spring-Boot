package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_console")
public class GameConsole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String name;

    private String productionCompany;

    //@Column(nullable = false)
    private int price;

    //@Column(nullable = false)
    private SupportedGameFormat supportedGameFormat;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "game_console_id"),
        inverseJoinColumns = @JoinColumn(name = "game_stop_shop_id"))
    List<GameStopShop> gameStopShops = new ArrayList<>();

    public GameConsole() {}

    public GameConsole(Long id, String name, String productionCompany, int price, SupportedGameFormat supportedGameFormat) {
        this.id = id;
        this.name = name;
        this.productionCompany = productionCompany;
        this.price = price;
        this.supportedGameFormat = supportedGameFormat;
    }

    public GameConsole(String name, String productionCompany, int price, SupportedGameFormat supportedGameFormat, List<GameStopShop> gameStopShops) {
        this.name = name;
        this.productionCompany = productionCompany;
        this.price = price;
        this.supportedGameFormat = supportedGameFormat;
        this.gameStopShops = gameStopShops;
    }

    public GameConsole(String name, String productionCompany, int price, SupportedGameFormat supportedGameFormat) {
        this.name = name;
        this.productionCompany = productionCompany;
        this.price = price;
        this.supportedGameFormat = supportedGameFormat;
    }

    public GameConsole(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SupportedGameFormat getSupportedGameFormat() {
        return supportedGameFormat;
    }

    public void setSupportedGameFormat(SupportedGameFormat supportedGameFormat) {
        this.supportedGameFormat = supportedGameFormat;
    }

    public List<GameStopShop> getGameStopShops() {
        return gameStopShops;
    }

    public void setGameStopShops(List<GameStopShop> gameStopShops) {
        this.gameStopShops = gameStopShops;
    }
}
