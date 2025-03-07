package com.example.forum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter @Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    public Player() {}

    public Player(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }
}
