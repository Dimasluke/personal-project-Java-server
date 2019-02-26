package com.example.demo.repository;

import com.example.demo.entities.ChampionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChampionRepository extends CrudRepository<ChampionEntity, Integer> {

    ChampionEntity findByNameIgnoreCase(String name);

}
