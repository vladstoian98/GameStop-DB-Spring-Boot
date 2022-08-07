package com.example.GameStopGradsProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee")
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

    public Employee() {}

    public Employee(Long id, String name, String address, int salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String name, String address, int salary, GameStopShop gameStopShop) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.gameStopShop = gameStopShop;
    }

    public Employee(String name, String address, int salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String name) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public GameStopShop getGameStopShop() {
        return gameStopShop;
    }

    public void setGameStopShop(GameStopShop gameStopShop) {
        this.gameStopShop = gameStopShop;
    }
}
