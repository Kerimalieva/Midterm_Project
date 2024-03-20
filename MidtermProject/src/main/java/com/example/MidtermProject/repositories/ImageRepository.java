package com.example.MidtermProject.repositories;

import com.example.MidtermProject.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}