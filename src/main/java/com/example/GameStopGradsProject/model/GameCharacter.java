package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_character")
@Data
@NoArgsConstructor
public class GameCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int level;

    private int age;
    @Column(name = "class_type")
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @Column(name = "race_type")
    @Enumerated(EnumType.STRING)
    private RaceType raceType;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "game_character_id"),
            inverseJoinColumns = @JoinColumn(name = "video_game_id"))
    private List<VideoGame> videoGames = new ArrayList<>();

    public GameCharacter(String name) {
        this.name = name;
    }
}
