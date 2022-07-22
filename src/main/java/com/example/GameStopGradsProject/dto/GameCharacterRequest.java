package com.example.GameStopGradsProject.dto;

import com.example.GameStopGradsProject.model.ClassType;
import com.example.GameStopGradsProject.model.RaceType;
import com.example.GameStopGradsProject.model.VideoGame;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class GameCharacterRequest {

    @NotNull
    private String name;

    private int level;

    private int age;

    @NotNull
    private ClassType classType;

    @NotNull
    private RaceType raceType;

    private List<VideoGameRequest> videoGames;

    public GameCharacterRequest(String name, int level, int age, ClassType classType, RaceType raceType) {
        this.name = name;
        this.level = level;
        this.age = age;
        this.classType = classType;
        this.raceType = raceType;
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

    public List<VideoGameRequest> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<VideoGameRequest> videoGames) {
        this.videoGames = videoGames;
    }
}
