package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "userEntity", orphanRemoval = true)
    @JsonIgnore
    private List<TeamEntity> teamEntityList = new ArrayList<>();

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password field is required")
    private String password;
    @Transient

    private Date create_At;
    private Date update_At;

    @PrePersist
    protected void onCreate() { this.create_At = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.update_At = new Date(); }

    // OneToMany with Teams

    public UserEntity () {
    }

    /*
    UserDetails interface methods
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public List<TeamEntity> getTeamEntityList() {
        return teamEntityList;
    }

    public void setTeamEntityList(List<TeamEntity> teamEntityList) {
        this.teamEntityList = teamEntityList;
    }

}
