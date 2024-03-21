package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.ProductDTO;
import com.example.MidtermProject.Mapper.ProductMapper;
import com.example.MidtermProject.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    @Test
    public void testToProductDTO() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Banana");
        product.setDescription("Good for your health");
        product.setPrice(100);
        product.setCity("Uganda");
        product.setDateOfCreated(LocalDateTime.now());

        ProductDTO productDTO = productMapper.toProductDTO(product);

        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getTitle(), productDTO.getTitle());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getCity(), productDTO.getCity());
        assertEquals(product.getDateOfCreated(), productDTO.getDateOfCreated());
    }
}
