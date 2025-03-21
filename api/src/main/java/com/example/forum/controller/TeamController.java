package com.example.forum.controller;

import com.example.forum.service.TeamService;

import reactor.core.publisher.Mono;

import com.example.forum.model.Team;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public Mono<List<Map<String, Object>>> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Mono<String> getTeamById(@PathVariable Long id) {
        System.out.println("The ID is: " + id);
        return teamService.getTeamById(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {  // FIX: Renamed method
        System.out.println("Received POST request: " + team.getName() + ", " + team.getCity());
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {  // FIX: Changed to @PathVariable
        teamService.deleteTeam(id);
    }
}
