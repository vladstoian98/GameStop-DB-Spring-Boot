package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video_game")
@Data
@NoArgsConstructor
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @Column(name = "game_genre")
    @Enumerated(EnumType.STRING)
    private GameGenre gameGenre;

    @ManyToMany(mappedBy = "videoGames", cascade = CascadeType.PERSIST)
    private List<GameCharacter> gameCharacters = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "video_game_id"),
            inverseJoinColumns = @JoinColumn(name = "game_stop_shop_id"))
    private List<GameStopShop> gameStopShops = new ArrayList<>();

    public VideoGame(String name) {
        this.name = name;
    }
}
