package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_character")
public class GameCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int level;

    private int age;
    //@Column(nullable = false, name = "class_type")
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    //@Column(nullable = false, name = "race_type")
    @Enumerated(EnumType.STRING)
    private RaceType raceType;


    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "game_character_id"),
            inverseJoinColumns = @JoinColumn(name = "video_game_id"))
    @Column(nullable = false, name = "video_games")
    private List<VideoGame> videoGames = new ArrayList<>();

    public GameCharacter() {}

    public GameCharacter(String name, int level, int age, ClassType classType, RaceType raceType) {
        this.name = name;
        this.level = level;
        this.age = age;
        this.classType = classType;
        this.raceType = raceType;
    }

    public GameCharacter(String name, int level, int age, ClassType classType,
                         RaceType raceType, List<VideoGame> videoGames) {
        this.name = name;
        this.level = level;
        this.age = age;
        this.classType = classType;
        this.raceType = raceType;
        this.videoGames = videoGames;
    }

    public GameCharacter(Long id, String name, int level, int age,
                         ClassType classType, RaceType raceType) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.age = age;
        this.classType = classType;
        this.raceType = raceType;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public List<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }
}
