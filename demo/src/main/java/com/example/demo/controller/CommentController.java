package com.example.demo.controller;

import com.example.demo.entities.CommentEntity;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping(value = "/comments")
    CommentEntity newComment(@RequestBody CommentEntity newComment){

        return commentRepository.save(newComment);
    }

}
