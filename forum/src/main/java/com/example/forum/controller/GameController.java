package com.example.forum.controller;

import com.example.forum.model.Game;
import com.example.forum.service.GameService;

import reactor.core.publisher.Mono;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/date/{date}")
    public List<Game> getGamesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return gameService.getGamesByDate(date);
    }

    // 🔹 Obtener un partido por ID
    @GetMapping("/{id}")
    public Optional<Game> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    @GetMapping("/today")
    public Mono<List<Map<String, Object>>> getGamesForToday() {
        return gameService.getGamesForToday();
    }

    // 🔹 Guardar un nuevo partido
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }
}
