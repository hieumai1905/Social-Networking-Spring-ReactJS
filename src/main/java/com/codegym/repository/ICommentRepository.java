package com.codegym.repository;

import com.codegym.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long id);
    void deleteAllByPostId(Long id);
}
