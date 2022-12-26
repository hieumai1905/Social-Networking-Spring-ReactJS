package com.codegym.service.image;

import com.codegym.model.Image;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    List<Image> findAllByPostId(Long id);
}
