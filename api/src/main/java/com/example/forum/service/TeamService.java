package com.example.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.forum.repository.TeamRepository;
import com.example.forum.model.Team;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    private final WebClient webClient;
    private static final String API_URL_TEAMS = "https://api.balldontlie.io/v1/teams";
    
    public TeamService(WebClient.Builder webClientBuilder,
                        @Value("${balldontlie.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(API_URL_TEAMS).defaultHeader("Authorization", apiKey).build();
    }
    
    @SuppressWarnings("unchecked")
    public Mono<List<Map<String, Object>>> getAllTeams() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (List<Map<String, Object>>) response.get("data"));
    }

    public Mono<String> getTeamById(Long teamId) {
        return webClient.get()
                .uri("/{id}", teamId)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
