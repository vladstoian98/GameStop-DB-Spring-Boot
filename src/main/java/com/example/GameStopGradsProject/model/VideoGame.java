package com.example.GameStopGradsProject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video_game")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, name = "game_genre")
    @Enumerated(EnumType.STRING)
    private GameGenre gameGenre;

    @ManyToMany(mappedBy = "videoGames", cascade = CascadeType.PERSIST)
    private List<GameCharacter> gameCharacters = new ArrayList<>();

    public VideoGame() {}

    public VideoGame(String name, int price, GameGenre gameGenre, List<GameCharacter> gameCharacters) {
        this.name = name;
        this.price = price;
        this.gameGenre = gameGenre;
        this.gameCharacters = gameCharacters;
    }

    public VideoGame(Long id, String name, int price, GameGenre gameGenre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gameGenre = gameGenre;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(List<GameCharacter> gameCharacters) {
        this.gameCharacters = gameCharacters;
    }
}
