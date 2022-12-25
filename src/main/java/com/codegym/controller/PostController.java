package com.codegym.controller;

import com.codegym.model.Post;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
@PropertySource("classpath:application.properties")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            return new ResponseEntity<Object>(post, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("NULL", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post currentPost = postService.findById(id);
        if (currentPost != null) {
            postService.save(post);
            return new ResponseEntity<>(currentPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            postService.delete(id);
            return new ResponseEntity<Object>(post, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("NULL", HttpStatus.NOT_FOUND);
    }
}