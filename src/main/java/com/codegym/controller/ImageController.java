package com.codegym.controller;

import com.codegym.model.Image;
import com.codegym.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
@PropertySource("classpath:application.properties")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping("/avatar/user/{id}")
    public ResponseEntity<List<Image>> getAllImagesAvatarWithUserId(@PathVariable Long id) {
        List<Image> images = imageService.findAllByPostId(id);
        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }
}
