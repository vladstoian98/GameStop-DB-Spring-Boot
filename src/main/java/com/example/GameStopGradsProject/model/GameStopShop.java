package com.example.GameStopGradsProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_stop_shop")
@Data
@NoArgsConstructor
public class GameStopShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "main_theme")
    @Enumerated(EnumType.STRING)
    private MainTheme mainTheme;

    @Column(name = "chief_details")
    private String chiefDetails = CEO.getInstance().toString();

    @ManyToMany(mappedBy = "gameStopShops", cascade = CascadeType.PERSIST)
    private List<VideoGame> videoGames = new ArrayList<>();

    @ManyToMany(mappedBy = "gameStopShops", cascade = CascadeType.PERSIST)
    private List<GameConsole> gameConsoles = new ArrayList<>();

    @OneToMany(mappedBy = "gameStopShop")
    private List<Employee> employees = new ArrayList<>();

    public GameStopShop(String streetName) {
        this.streetName = streetName;
    }
}
