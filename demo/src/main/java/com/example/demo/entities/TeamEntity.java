package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private String teamOwner;

    @ManyToMany
    @JoinTable(
            name = "team_list",
            joinColumns = {@JoinColumn(name="team_id")},
            inverseJoinColumns = {@JoinColumn(name="champion_id")}
    )
    @JsonManagedReference
    private List<ChampionEntity> champions;

    @ManyToOne
    @JsonIgnore
    private UserEntity userEntity;


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
