package com.example.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.forum.repository.PlayerRepository;
import com.example.forum.repository.TeamRepository;

import reactor.core.publisher.Mono;

import com.example.forum.model.Player;
import com.example.forum.model.Team;

import java.util.List;
import java.util.Map;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final WebClient webClient;
    private static final String API_URL_PLAYERS = "https://api.balldontlie.io/v1/players";
    
    public PlayerService(WebClient.Builder webClientBuilder,
                        @Value("${balldontlie.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(API_URL_PLAYERS).defaultHeader("Authorization", apiKey).build();
    }

    public Mono<Object> getPlayersByTeam(Long teamId)  {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("team_ids[]", teamId) // API requires an array for team IDs
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (List<Map<String, Object>>) response.get("data"));
    }

    public Mono<List<Map<String, Object>>> getAllPlayers() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (List<Map<String, Object>>) response.get("data"));
    }

    public Player savePlayer(Player player) {
        Team team = teamRepository.findById(player.getTeam().getId())
                                  .orElseThrow(() -> new RuntimeException("Team not found"));

        player.setTeam(team);                                  
        return playerRepository.save(player);
    }
}
