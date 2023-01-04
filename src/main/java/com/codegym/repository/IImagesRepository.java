package com.codegym.repository;

import com.codegym.model.Image;
import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagesRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPostId(Long id);

    @Query(value = "select * from ", nativeQuery = true)
    List<Image> findAllAvatarByUserId(@Param("id") Long id);

    @Query(value = "select * from ", nativeQuery = true)
    List<Image> findAllCoverByUserId(@Param("id") Long id);
}
