package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.ImageDTO;
import com.example.MidtermProject.models.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageDTO toImageDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setName(image.getName());
        imageDTO.setOriginalFileName(image.getOriginalFileName());
        imageDTO.setSize(image.getSize());
        imageDTO.setContentType(image.getContentType());
        return imageDTO;
    }
}