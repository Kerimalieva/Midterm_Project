package com.example.MidtermProject;

import com.example.MidtermProject.controllers.ProductController;
import com.example.MidtermProject.models.Image;
import com.example.MidtermProject.models.Product;
import com.example.MidtermProject.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void products() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);

        when(productService.listProducts(null)).thenReturn(Collections.emptyList());
        when(productService.getUserByPrincipal(principal)).thenReturn(null);

        String viewName = productController.products(null, principal, model);

        assertEquals("products", viewName);
        verify(model).addAttribute("products", Collections.emptyList());
        verify(model).addAttribute("user", null);
        verify(model).addAttribute("searchWord", null);
    }

    @Test
    void productInfo() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        Product product = new Product();

        when(productService.getProductById(1L)).thenReturn(product);
        when(productService.getUserByPrincipal(principal)).thenReturn(null);

        String viewName = productController.productInfo(1L, model, principal);

        assertEquals("product-info", viewName);
        verify(model).addAttribute("product", product);
        verify(model).addAttribute("user", null);
        verify(model).addAttribute("images", product.getImages());
        verify(model).addAttribute("authorProduct", product.getUser());
    }

    @Test
    void createProduct() throws Exception {
        Principal principal = mock(Principal.class);
        MultipartFile file1 = new MockMultipartFile("file1", "filename1.txt", "text/plain", "some xml".getBytes());
        MultipartFile file2 = new MockMultipartFile("file2", "filename2.txt", "text/plain", "some xml".getBytes());
        MultipartFile file3 = new MockMultipartFile("file3", "filename3.txt", "text/plain", "some xml".getBytes());
        Product product = new Product();

        String viewName = productController.createProduct(file1, file2, file3, product, principal);

        assertEquals("redirect:/my/products", viewName);
        verify(productService).saveProduct(principal, product, file1, file2, file3);
    }

    @Test
    void deleteProduct() {
        Principal principal = mock(Principal.class);

        String viewName = productController.deleteProduct(1L, principal);

        assertEquals("redirect:/my/products", viewName);
        verify(productService).deleteProduct(null, 1L);
    }

    @Test
    void userProducts() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        when(productService.getUserByPrincipal(principal)).thenReturn(null);

        String viewName = productController.userProducts(principal, model);

        assertEquals("my-products", viewName);
        verify(model).addAttribute("user", null);
        verify(model).addAttribute("products", Collections.emptyList());
    }
}
