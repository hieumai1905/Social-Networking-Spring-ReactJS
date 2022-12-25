package com.codegym.repository;

import com.codegym.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(Long id);
}
