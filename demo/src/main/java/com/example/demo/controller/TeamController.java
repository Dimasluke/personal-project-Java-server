package com.example.demo.controller;

import com.example.demo.entities.ChampionEntity;
import com.example.demo.entities.TeamEntity;
import com.example.demo.repository.ChampionRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ChampionRepository championRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/teams")
    public Iterable<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(value = "/teams/{id}")
    public TeamEntity getTeam(@PathVariable Integer id) {
        System.out.println("getTeamController hit controller hit");
        TeamEntity currentTeam = teamRepository.findById(id).orElse(null);
        return currentTeam;
    }

    @PostMapping(value = "/teams")
    public TeamEntity saveTeam(@RequestBody Map<String, String> team, Principal principal) {
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

        return teamRepository.save(newTeam);

    }
//
    @PutMapping(value = "/teams/{id}")
    public TeamEntity updateTeam(@RequestBody Map<String, String> team, @PathVariable Integer id) {
        TeamEntity currentTeam = teamRepository.findById(id).orElse(null);
        System.out.println("================="+ team);
        System.out.println(id);
        currentTeam.setTeamName(team.get("teamName"));
        List<ChampionEntity> champions = new ArrayList<>();
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion1"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion2"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion3"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion4"))).orElse(null));
        champions.add(championRepository.findById(Integer.parseInt(team.get("champion5"))).orElse(null));
        currentTeam.setChampions(champions);

        teamRepository.save(currentTeam);

        return currentTeam;
    }

//    @PutMapping(value = "/teams/{id}")
//    public String updateTeam(@RequestBody Map<String, String > team, @PathVariable Integer id, Principal principal) {
//        System.out.println(team);
//        System.out.println(id);
//        System.out.println("Route hit");
//        teamRepository.findById(id)
//                .map(currentTeam -> {
//                    currentTeam.setTeamName(team.get("teamName"));
//                    currentTeam.setTeamOwner(principal.getName());
//                    List<ChampionEntity> champions = new ArrayList<>();
//                        champions.add(championRepository.findById(Integer.parseInt(team.get("champion1"))).orElse(null));
//                        champions.add(championRepository.findById(Integer.parseInt(team.get("champion2"))).orElse(null));
//                        champions.add(championRepository.findById(Integer.parseInt(team.get("champion3"))).orElse(null));
//                        champions.add(championRepository.findById(Integer.parseInt(team.get("champion4"))).orElse(null));
//                        champions.add(championRepository.findById(Integer.parseInt(team.get("champion5"))).orElse(null));
//                        currentTeam.setChampions(champions);
//                        System.out.println(currentTeam);
//                        return teamRepository.save(currentTeam);
//                })
//                .orElseGet(() -> {
//                    return null;
//                });
//
//        return "success";
//    }

    @DeleteMapping(value = "/teams/{id}")
    void deleteTeam(@PathVariable Integer id){
        System.out.println("delete route hit");
        teamRepository.deleteById(id);
    }
}
