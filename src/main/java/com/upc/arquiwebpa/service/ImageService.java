package com.upc.arquiwebpa.service;

import com.upc.arquiwebpa.entities.Image;
import com.upc.arquiwebpa.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    private final PlatformTransactionManager transactionManager;

    public ImageService(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.imageRepository = imageRepository;
    }

    public Image uploadImage(Image image) {
     return imageRepository.save(image);
    }
    public List<Image> listImage(){
        /*List<byte[]> l1 = new ArrayList<>();

        for (Image image : imageRepository.findAll()) {
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            if (image != null) {
                byte[] imageData = transactionTemplate.execute(status -> {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta el tipo MIME según el formato de tus imágenes
                return image.getImageData();
                });
                l1.add(imageData);
            }
        }*/
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
