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

    // getAllImagesAvatarWithUserId dùng để lấy tất cả ảnh avatar của user
    @GetMapping("/avatar/user/{id}")
    public ResponseEntity<List<Image>> getAllImagesAvatarWithUserId(@PathVariable Long id) {
        List<Image> images = imageService.findAllAvatarByUserId(id);
        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    @GetMapping("cover/user/{id}")
    public ResponseEntity<List<Image>> getImageCoverWithUserId(@PathVariable Long id) {
        List<Image> images = imageService.findAllCoverByUserId(id);
        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }
}
