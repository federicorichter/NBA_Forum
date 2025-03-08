package com.example.forum.controller;

import com.example.forum.model.Player;
import com.example.forum.service.PlayerService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public Mono<List<Map<String, Object>>> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/team/{teamId}")
    public Mono<Object> getPlayersByTeam(@PathVariable long teamId) {
        return playerService.getPlayersByTeam(teamId);
    }

    // 🔹 Guardar un nuevo jugador
    @PostMapping
    public Player createPlayer(@RequestBody Player Player) {
        return playerService.savePlayer(Player);
    }
}
