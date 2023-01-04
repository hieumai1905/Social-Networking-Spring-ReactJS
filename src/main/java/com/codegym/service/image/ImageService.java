package com.codegym.service.image;

import com.codegym.model.Image;
import com.codegym.repository.IImagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ImageService implements IImageService {

    @Autowired
    private IImagesRepository imagesRepository;

    @Override
    public List<Image> findAll() {
        return imagesRepository.findAll();
    }

    @Override
    public boolean save(Image image) {
        try {
            imagesRepository.save(image);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            imagesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Image findById(Long id) {
        return imagesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> findAllByPostId(Long id) {
        return imagesRepository.findAllByPostId(id);
    }

    @Override
    public List<Image> findAllAvatarByUserId(Long id) {
        return imagesRepository.findAllAvatarByUserId(id);
    }

    @Override
    public List<Image> findAllCoverByUserId(Long id) {
        return imagesRepository.findAllCoverByUserId(id);
    }
}
