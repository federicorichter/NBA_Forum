package com.example.forum.controller;

import com.example.forum.model.Player;
import com.example.forum.service.PlayerService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayersByTeam(@RequestParam Long teamId) {
        return playerService.getPlayersByTeam(teamId);
    }

    // 🔹 Guardar un nuevo jugador
    @PostMapping
    public Player createPlayer(@RequestBody Player Player) {
        return playerService.savePlayer(Player);
    }
}
