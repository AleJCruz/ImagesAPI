package com.upc.arquiwebpa.repository;

import com.upc.arquiwebpa.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
