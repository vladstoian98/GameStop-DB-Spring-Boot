package com.example.GameStopGradsProject.model;

public class CEO {

    private static CEO chief = null;

    private String name;

    private int age;

    private CEO() {
        name = "Stoian Vlad";
        age = 20;
    }

    public static CEO getInstance() {

        if(chief == null)
            chief = new CEO();

        return chief;
    }

    @Override
    public String toString() {
        return "CEO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
