package com.example.demo.repository;

import com.example.demo.entities.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    Page<CommentEntity> findByTeamEntityId(Integer teamEntityId, Pageable pageable);
    Optional<CommentEntity> findByIdAndTeamEntityId(Integer id, Integer teamEntityId);
}
