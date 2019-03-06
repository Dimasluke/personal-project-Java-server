package com.example.demo.controller;

import com.example.demo.entities.ChampionEntity;
import com.example.demo.entities.TeamEntity;
import com.example.demo.repository.ChampionRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ChampionRepository championRepository;

    @GetMapping(value = "/teams")
    public Iterable<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(value = "/teams/{id}")
    public Optional<TeamEntity> getTeam(@PathVariable Integer id) {
        System.out.println("getTeamController hit controller hit");
        return teamRepository.findById(id);
    }

    @PostMapping(value = "/teams")
    public String saveTeam(@RequestBody Map<String, String> team, Principal principal) {
        System.out.println(team);
        TeamEntity newTeam = new TeamEntity();
        newTeam.setTeamName(team.get("teamName"));
        newTeam.setTeamOwner(principal.getName());

        List<ChampionEntity> champions = new ArrayList<>();
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion1"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion2"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion3"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion4"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion5"))).orElse(null));
        newTeam.setChampions(champions);

        teamRepository.save(newTeam);

        return "Success";
    }
}
