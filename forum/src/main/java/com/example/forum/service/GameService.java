package com.example.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forum.repository.GameRepository;
import com.example.forum.repository.TeamRepository;
import com.example.forum.model.Game;
import com.example.forum.model.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Game> getGamesByDate(LocalDate date) {
        return gameRepository.findByDate(date);
    }

    public Game saveGame(Game game) {
        System.out.println("Away Team: " + game.getAwayTeam().getId());
        Team awayTeam = teamRepository.findById(game.getAwayTeam().getId())
                                  .orElseThrow(() -> new RuntimeException("Team not found"));

        game.setAwayTeam(awayTeam); 
        System.out.println("Local Team: " + game.getLocalTeam());

        Team localTeam = teamRepository.findById(game.getLocalTeam().getId())
                                  .orElseThrow(() -> new RuntimeException("Team not found"));

        game.setLocalTeam(localTeam); 
        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(Long id) { // Agrego método útil
        return gameRepository.findById(id);
    }
}
