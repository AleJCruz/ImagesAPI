package com.upc.arquiwebpa.controller;

import com.upc.arquiwebpa.dtos.ImageDTO;
import com.upc.arquiwebpa.entities.Image;
import com.upc.arquiwebpa.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file != null) {
                Image image = new Image();
                image.setFilename(file.getOriginalFilename());
                image.setImageData(file.getBytes());
                imageService.uploadImage(image);
                return new ResponseEntity<>("imagen guardada correctamente", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        return new ResponseEntity<>(convertToListDTO(imageService.listImage()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ImageDTO convertToDTO(Image image){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(image, ImageDTO.class);
    }

    private List<ImageDTO> convertToListDTO(List<Image> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


}
