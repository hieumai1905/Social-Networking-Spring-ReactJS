package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Like;
import com.codegym.model.Post;
import com.codegym.service.appUserService.IAppUserService;
import com.codegym.service.like.ILikeService;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/likes")
@PropertySource("classpath:application.properties")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IPostService postService;

    @GetMapping("/{postId}/post/{userId}/user")
    public ResponseEntity<Like> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        List<Like> likes = likeService.findAllByPostId(postId);
        Post post = postService.findById(postId);
        AppUser user = appUserService.findById(userId);
        if (likes.stream().noneMatch(like -> Objects.equals(like.getUser().getId(), userId))) {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeService.save(like);
            return ResponseEntity.ok(like);
        } else {
            Like like = likes.stream().filter(like1 -> Objects.equals(like1.getUser().getId(), userId)).findFirst().get();
            likeService.delete(like.getId());
            return ResponseEntity.ok(like);
        }
    }
}
