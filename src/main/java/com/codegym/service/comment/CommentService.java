package com.codegym.service.comment;

import com.codegym.model.Comment;
import com.codegym.repository.ICommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public boolean save(Comment comment) {
        try {
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findAllByPostId(Long id) {
        return commentRepository.findAllByPostId(id);
    }

    @Override
    public void deleteAllByPostId(Long id) {
        commentRepository.deleteAllByPostId(id);
    }
}
