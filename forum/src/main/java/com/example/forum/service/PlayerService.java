package com.example.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forum.repository.PlayerRepository;
import com.example.forum.repository.TeamRepository;
import com.example.forum.model.Player;
import com.example.forum.model.Team;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Player> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public Player savePlayer(Player player) {
        Team team = teamRepository.findById(player.getTeam().getId())
                                  .orElseThrow(() -> new RuntimeException("Team not found"));

        player.setTeam(team);                                  
        return playerRepository.save(player);
    }
}
