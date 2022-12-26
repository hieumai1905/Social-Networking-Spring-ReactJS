package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from `posts` join `status` on `posts`.`status_id` = `status`.`id_status` where `status`.`status_name` ='public' and `posts`.`app_user_id` = :id", nativeQuery = true)
    Iterable<Post> findAllPostStatusIsPublicOfUserId(@Param("id") Long id);

    @Query(value = "select * from `posts` join `status` on `posts`.`status_id` = `status`.`id_status` where `status`.`status_name` ='public' and `posts`.`app_user_id` = :idUser and `posts`.id = :idPost", nativeQuery = true)
    Optional<Post> findPostByUserIdAndPostId(@Param("idUser") Long idUser, @Param("idPost") Long id);
}

