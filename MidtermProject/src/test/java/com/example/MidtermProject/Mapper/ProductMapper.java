package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.ProductDTO;
import com.example.MidtermProject.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCity(product.getCity());
        productDTO.setDateOfCreated(product.getDateOfCreated());
        return productDTO;
    }
}