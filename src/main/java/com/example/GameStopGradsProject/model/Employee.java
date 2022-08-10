package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private int salary;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_stop_shop_id")
    private GameStopShop gameStopShop;

    public Employee(String name) {
        this.name = name;
    }
}
