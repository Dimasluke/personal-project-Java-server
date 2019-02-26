package com.example.demo.controller;


import com.example.demo.entities.ChampionEntity;
import com.example.demo.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionController {

    @Autowired
    ChampionRepository championRepository;

    @GetMapping(value = "/api/champions")
    public Iterable<ChampionEntity> getAllChampions() {
        return championRepository.findAll();
    }

    @GetMapping(value = "/api/champions/{name}")
    public ChampionEntity getChampions(@PathVariable String name) {
        System.out.println("getChampion controller hit");
        return championRepository.findByNameIgnoreCase(name);
    }

}
