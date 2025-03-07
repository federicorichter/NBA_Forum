package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forum.model.Game;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDate(LocalDate date);  // Corrige `findByFecha` → `findByDate`
}
