package com.example.demo.repository;

import com.example.demo.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;


public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
}
