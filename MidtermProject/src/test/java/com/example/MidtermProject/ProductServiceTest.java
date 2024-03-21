package com.example.MidtermProject;

import com.example.MidtermProject.models.Image;
import com.example.MidtermProject.models.Product;
import com.example.MidtermProject.models.User;
import com.example.MidtermProject.repositories.ProductRepository;
import com.example.MidtermProject.repositories.UserRepository;
import com.example.MidtermProject.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    Product product = new Product();
    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    private Principal principal;
    private MultipartFile file;



    @Test
    void testListProducts_WithTitle() {
        // Arrange
        String title = "Apple";
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findByTitle(title)).thenReturn(expectedProducts);


        List<Product> actualProducts = productService.listProducts(title);

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testListProducts_WithoutTitle() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listProducts(null);

        assertEquals(expectedProducts, actualProducts);
    }




    @Test
    void testDeleteProduct() {

        User user = new User();
        user.setId(1L);
        product.setUser(user);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(user, 1L);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testGetProductById() {
        long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product actualProduct = productService.getProductById(productId);

        assertEquals(product, actualProduct);
    }

}
