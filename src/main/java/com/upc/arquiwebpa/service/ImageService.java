package com.upc.arquiwebpa.service;

import com.upc.arquiwebpa.entities.Image;
import java.util.List;


public interface ImageService {

    public Image uploadImage(Image image);
    public List<Image> listImage();

    public Image deleteImage(Long id);
}
