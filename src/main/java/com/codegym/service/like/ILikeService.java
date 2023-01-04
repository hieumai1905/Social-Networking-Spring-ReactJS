package com.codegym.service.like;

import com.codegym.model.Like;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ILikeService extends IGeneralService<Like> {
    List<Like> findAllByPostId(Long id);
    void deleteAllByPostId(Long id);
    void deleteAllByCommentId(Long id);
    List<Like> findAllByPostIdAndCommentIdIsNotNull(Long id);
    List<Like> findAllByPostIdAndCommentIdIsNull(Long id);
    List<Like> findAllByPostIdAndCommentId(Long postId, Long commentId);
}
