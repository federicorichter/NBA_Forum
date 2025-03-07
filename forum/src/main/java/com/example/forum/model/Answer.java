package com.example.forum.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_od", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();
}
