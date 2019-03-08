package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ChampionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int damage;
    private int toughness;
    private int crowdControl;
    private int utility;
    private int mobility;
    private String iconUrl;
    private String embedString;


    @ManyToMany(mappedBy = "champions")
    @JsonBackReference
    private List<TeamEntity> teams;
}
