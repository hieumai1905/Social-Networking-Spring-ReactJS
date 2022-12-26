package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllPostStatusIsPublic(Long id);

    Optional<Post> findPostByUserIdAndPostId(Long idUser, Long idPost);
}
