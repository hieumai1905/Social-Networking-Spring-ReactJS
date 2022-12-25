package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Comment;
import com.codegym.model.Like;
import com.codegym.model.Post;
import com.codegym.service.appUserService.IAppUserService;
import com.codegym.service.comment.ICommentService;
import com.codegym.service.like.ILikeService;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
@PropertySource("classpath:application.properties")
public class PostController {
    @Autowired
    private ILikeService likeService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

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
            currentPost.setContent(post.getContent());
            postService.save(currentPost);
            return new ResponseEntity<>(currentPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            likeService.deleteAllByPostId(id);
            commentService.deleteAllByPostId(id);
            postService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{postId}/like/users/{userId}")
    public ResponseEntity<Like> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        List<Like> likes = likeService.findAllByPostIdAndCommentIdIsNull(postId);
        Post post = postService.findById(postId);
        AppUser user = appUserService.findById(userId);
        if (likes.stream().noneMatch(like -> Objects.equals(like.getUser().getId(), userId))) {
            Like like = new Like();
            like.setPost(post);
            like.getPost().setLikeCount(like.getPost().getLikeCount() + 1);
            like.setUser(user);
            likeService.save(like);
            return ResponseEntity.ok(like);
        } else {
            Like like = likes.stream().filter(like1 -> Objects.equals(like1.getUser().getId(), userId)).findFirst().get();
            like.getPost().setLikeCount(like.getPost().getLikeCount() - 1);
            likeService.save(like);
            likeService.delete(like.getId());
            return ResponseEntity.ok(like);
        }
    }

    @GetMapping("/{postId}/cmts/{idCmt}/like/users/{userId}")
    public ResponseEntity<Like> likeComment(@PathVariable Long postId, @PathVariable Long idCmt, @PathVariable Long userId) {
        List<Like> likes = likeService.findAllByPostIdAndCommentIdIsNotNull(postId);
        Post post = postService.findById(postId);
        if (likes.stream().anyMatch(like -> Objects.equals(like.getUser().getId(), userId) && Objects.equals(like.getComment().getId(), idCmt))) {
            Like like = likes.stream().filter(like1 -> Objects.equals(like1.getUser().getId(), userId) && Objects.equals(like1.getComment().getId(), idCmt)).findFirst().get();
            like.getComment().setLikeCount(like.getComment().getLikeCount() - 1);
            likeService.save(like);
            likeService.delete(like.getId());
            return ResponseEntity.ok(like);
        } else {
            Comment comment = commentService.findById(idCmt);
            AppUser user = appUserService.findById(userId);
            Like like = new Like();
            like.setPost(post);
            like.setComment(comment);
            like.getComment().setLikeCount(like.getComment().getLikeCount() + 1);
            like.setUser(user);
            likeService.save(like);
            return ResponseEntity.ok(like);
        }
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<Like>> getAllLikeByPostId(@PathVariable Long postId) {
        List<Like> likes = likeService.findAllByPostId(postId);
        if (likes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{postId}/cmts")
    public ResponseEntity<List<Comment>> getAllByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.findAllByPostId(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{postId}/cmts/users/{userId}")
    public ResponseEntity<Comment> commentPost(@PathVariable Long postId, @PathVariable Long userId, @RequestBody Comment comment) {
        Post post = postService.findById(postId);
        AppUser user = appUserService.findById(userId);
        post.setCommentCount(post.getCommentCount() + 1);
        comment.setPost(post);
        comment.setUser(user);
        postService.save(post);
        commentService.save(comment);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{postId}/cmts/{cmtId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long postId, @PathVariable Long cmtId, @RequestBody Comment comment) {
        Post post = postService.findById(postId);
        Comment currentComment = commentService.findById(cmtId);
        if (currentComment != null && post != null) {
            currentComment.setContent(comment.getContent());
            commentService.save(currentComment);
            return new ResponseEntity<>(currentComment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{postId}/cmts/{cmtId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long postId, @PathVariable Long cmtId) {
        Post post = postService.findById(postId);
        Comment comment = commentService.findById(cmtId);
        if (comment != null && post != null) {
            post.setCommentCount(post.getCommentCount() - 1);
            likeService.deleteAllByCommentId(cmtId);
            postService.save(post);
            commentService.delete(cmtId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}