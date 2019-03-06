package com.example.demo.services;

import com.example.demo.entities.TeamEntity;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamEntity saveTeam(TeamEntity teamEntity){
        return teamRepository.save(teamEntity);
    }
}
