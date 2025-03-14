package com.example.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.forum.repository.GameRepository;
import com.example.forum.repository.TeamRepository;

import reactor.core.publisher.Mono;

import com.example.forum.model.Game;
import com.example.forum.model.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final WebClient webClient;
    private static final String API_URL_GAMES = "https://api.balldontlie.io/v1/games";

    public GameService(WebClient.Builder webClientBuilder,
                        @Value("${balldontlie.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(API_URL_GAMES).defaultHeader("Authorization", apiKey).build();
    }
    

    public List<Game> getGamesByDate(LocalDate date) {
        return gameRepository.findByDate(date);
    }

    public Mono<List<Map<String, Object>>> getGamesForToday() {
        String todayDate = LocalDate.now().toString(); // Format: YYYY-MM-DD
        
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        //.path("/games")
                        .queryParam("dates[]", todayDate)
                        .build())
                //.headers(h -> h.setBearerAuth(apiKey))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (List<Map<String, Object>>) response.get("data"));
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
