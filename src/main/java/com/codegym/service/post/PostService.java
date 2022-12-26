package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.repository.IPostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public boolean save(Post post) {
        try {
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try{
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Post> findAllPostStatusIsPublic(Long id) {
        return postRepository.findAllPostStatusIsPublicOfUserId(id);
    }

    @Override
    public Optional<Post> findPostByUserIdAndPostId(Long idUser, Long idPost) {
        return postRepository.findPostByUserIdAndPostId(idUser, idPost);
    }
}
