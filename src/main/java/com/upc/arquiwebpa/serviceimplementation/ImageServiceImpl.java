package com.upc.arquiwebpa.serviceimpl;

import com.upc.arquiwebpa.entities.Image;
import com.upc.arquiwebpa.repository.ImageRepository;
import com.upc.arquiwebpa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    public Image uploadImage(Image image) {
        return imageRepository.save(image);
    }
    public List<Image> listImage(){
        return imageRepository.findAll();
    }

    public Image deleteImage(Long id){
        Image image = imageRepository.findById(id).orElse(null);
        if(image != null){
            imageRepository.delete(image);
        }
        return image;
    }
}
