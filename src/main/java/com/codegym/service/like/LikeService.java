package com.codegym.service.like;

import com.codegym.model.Like;
import com.codegym.repository.ILikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LikeService implements ILikeService {

    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public boolean save(Like like) {
        try {
            likeRepository.save(like);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Like like = likeRepository.findById(id).orElse(null);
        try {
            likeRepository.delete(like);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Like findById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Like> findAllByPostId(Long id) {
        return likeRepository.findAllByPostId(id);
    }

    @Override
    public void deleteAllByPostId(Long id) {
        likeRepository.deleteAllByPostId(id);
    }

    @Override
    public void deleteAllByCommentId(Long id) {
        likeRepository.deleteAllByCommentId(id);
    }

    @Override
    public List<Like> findAllByPostIdAndCommentIdIsNull(Long id) {
        return likeRepository.findAllByPostIdAndCommentIdIsNull(id);
    }

    @Override
    public List<Like> findAllByPostIdAndCommentIdIsNotNull(Long id) {
        return likeRepository.findAllByPostIdAndCommentIdIsNotNull(id);
    }
}
