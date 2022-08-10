package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_console")
@Data
@NoArgsConstructor
public class GameConsole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "production_company")
    private String productionCompany;

    private int price;

    @Column(name = "supported_game_format")
    @Enumerated(EnumType.STRING)
    private SupportedGameFormat supportedGameFormat;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "game_console_id"),
        inverseJoinColumns = @JoinColumn(name = "game_stop_shop_id"))
    List<GameStopShop> gameStopShops = new ArrayList<>();

    public GameConsole(String name) {
        this.name = name;
    }
}
