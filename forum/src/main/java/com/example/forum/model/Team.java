package com.example.forum.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<Player> players;

    // Default constructor required by JPA
    public Team() {}

    // Constructor for easy instantiation
    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
