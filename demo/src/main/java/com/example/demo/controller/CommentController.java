package com.example.demo.controller;

import com.example.demo.entities.CommentEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping(value = "/teams/{id}/comments")
    public Page<CommentEntity> getAllCommentsByTeamId(@PathVariable (value = "id") Integer teamId, Pageable pageable) {
        return commentRepository.findByTeamEntityId(teamId, pageable);
    }

    @PostMapping("/teams/{id}/comments")
    public CommentEntity createComment(@PathVariable (value = "id") Integer teamId,
                                       @Valid @RequestBody CommentEntity commentEntity) {
        return teamRepository.findById(teamId).map(team -> {
            commentEntity.setTeamEntity(team);
            return commentRepository.save(commentEntity);
        }).orElseThrow(null);
        }

    @PutMapping("/teams/{id}/comments/{commentId}")
    public CommentEntity updateComment(@PathVariable (value = "id") Integer teamId,
                                       @PathVariable (value = "commentId") Integer commentId,
                                       @Valid @RequestBody CommentEntity commentRequest) {
            if(!teamRepository.existsById(teamId)){
                throw new ResourceNotFoundException("TeamId " + teamId + " not found");
            }
            return commentRepository.findById(commentId).map(comment -> {
                comment.setText(commentRequest.getText());
                return commentRepository.save(comment);
            }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
        }

    @DeleteMapping("/teams/{id}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "id") Integer teamId,
                                           @PathVariable (value = "commentId") Integer commentId) {
        return commentRepository.findByIdAndTeamEntityId(commentId, teamId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + " and teamId " + teamId));
    }
    }
//
//    @PostMapping(value = "/teams/{id}/comments")
//    CommentEntity newComment(@RequestBody CommentEntity newComment){
//        System.out.println(newComment);
//        return commentRepository.save(newComment);
//    }


