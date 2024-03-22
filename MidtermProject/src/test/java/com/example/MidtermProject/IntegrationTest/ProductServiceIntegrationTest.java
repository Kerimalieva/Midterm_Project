package com.example.MidtermProject.IntegrationTest;

import com.example.MidtermProject.models.Image;
import com.example.MidtermProject.models.Product;
import com.example.MidtermProject.models.User;
import com.example.MidtermProject.repositories.ProductRepository;
import com.example.MidtermProject.repositories.UserRepository;
import com.example.MidtermProject.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductServiceIntegrationTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ProductService productService;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listProducts(null);

        assertEquals(expectedProducts.size(), actualProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        User user = new User();
        user.setId(1L);
        Product product = new Product();
        product.setId(1L);
        product.setUser(user);
        Optional<Product> optionalProduct = Optional.of(product);
        when(productRepository.findById(anyLong())).thenReturn(optionalProduct);

        productService.deleteProduct(user, 1L);

        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    void testGetProductById() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        Optional<Product> optionalProduct = Optional.of(expectedProduct);
        when(productRepository.findById(anyLong())).thenReturn(optionalProduct);

        Product actualProduct = productService.getProductById(1L);

        verify(productRepository, times(1)).findById(anyLong());
        assertEquals(expectedProduct, actualProduct);
    }
}
