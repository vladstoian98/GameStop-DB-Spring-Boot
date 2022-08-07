package com.example.GameStopGradsProject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_stop_shop")
public class GameStopShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   //@Column(nullable = false)
    private String streetName;

    //@Column(nullable = false)
    private int streetNumber;

    //@Column(nullable = false)
    private MainTheme mainTheme;

    private String chiefDetails = CEO.getInstance().toString();

    @ManyToMany(mappedBy = "gameStopShops", cascade = CascadeType.PERSIST)
    @Column(name = "video_games")
    private List<VideoGame> videoGames = new ArrayList<>();

    @ManyToMany(mappedBy = "gameStopShops", cascade = CascadeType.PERSIST)
    @Column(name = "game_consoles")
    private List<GameConsole> gameConsoles = new ArrayList<>();

    @OneToMany(mappedBy = "gameStopShop")
    private List<Employee> employees = new ArrayList<>();

    public GameStopShop() {}

    public GameStopShop(Long id, String streetName, int streetNumber, MainTheme mainTheme) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.mainTheme = mainTheme;
    }

    public GameStopShop(String streetName, int streetNumber, MainTheme mainTheme, List<VideoGame> videoGames, List<GameConsole> gameConsoles, List<Employee> employees) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.mainTheme = mainTheme;
        this.videoGames = videoGames;
        this.gameConsoles = gameConsoles;
        this.employees = employees;
    }

    public GameStopShop(String streetName, int streetNumber, MainTheme mainTheme) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.mainTheme = mainTheme;
    }

    public GameStopShop(String streetName) {
        this.streetName = streetName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public MainTheme getMainTheme() {
        return mainTheme;
    }

    public void setMainTheme(MainTheme mainTheme) {
        this.mainTheme = mainTheme;
    }

    public List<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }

    public List<GameConsole> getGameConsoles() {
        return gameConsoles;
    }

    public void setGameConsoles(List<GameConsole> gameConsoles) {
        this.gameConsoles = gameConsoles;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
