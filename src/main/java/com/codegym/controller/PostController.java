package com.codegym.controller;

import com.codegym.model.Account;
import com.codegym.model.Post;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
@PropertySource("classpath:application.properties")
public class PostController {
    @Autowired
    private IPostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            return new ResponseEntity<Object>(post, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("NULL", HttpStatus.NO_CONTENT);
    }
}
