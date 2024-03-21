package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.ImageDTO;
import com.example.MidtermProject.Mapper.ImageMapper;
import com.example.MidtermProject.models.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ImageMapperTest {
    @InjectMocks
    private ImageMapper imageMapper;

    @Test
    public void testToImageDTO() {
        Image image = new Image();
        image.setId(1L);
        image.setName("first_image");
        image.setOriginalFileName("first.jpg");
        image.setSize(1024L);
        image.setContentType("image/jpeg");

        ImageDTO imageDTO = imageMapper.toImageDTO(image);

        assertEquals(image.getId(), imageDTO.getId());
        assertEquals(image.getName(), imageDTO.getName());
        assertEquals(image.getOriginalFileName(), imageDTO.getOriginalFileName());
        assertEquals(image.getSize(), imageDTO.getSize());
        assertEquals(image.getContentType(), imageDTO.getContentType());
    }
}