package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String author;
    private String text;

    @ManyToOne
    @JoinColumn
    private TeamEntity teamEntity;

    private Date create_At;
    private Date update_At;

    @PrePersist
    protected void onCreate() { this.create_At = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.update_At = new Date(); }

}
