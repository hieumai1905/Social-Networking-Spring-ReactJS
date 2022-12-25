package com.codegym.service.comment;

import com.codegym.model.Comment;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {
    List<Comment> findAllByPostId(Long id);

    void deleteAllByPostId(Long id);
}
