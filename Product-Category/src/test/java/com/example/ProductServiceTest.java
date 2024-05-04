package com.example;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {

        Product product1 = new Product(1L, "Product 1", "Description 1", new BigDecimal("10.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Product product2 = new Product(2L, "Product 2", "Description 2", new BigDecimal("20.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));


        var result = productService.getAllProducts();


        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
    }

    @Test
    void testGetProductById() {

        Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", new BigDecimal("10.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        var result = productService.getProductById(productId);


        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Product 1", result.getName());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "New Product", "New Description", new BigDecimal("15.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Product savedProduct = new Product(1L, "New Product", "New Description", new BigDecimal("15.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);


        var result = productService.createProduct(product);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Product", result.getName());
    }

    @Test
    void testUpdateProduct() {

        Long productId = 1L;
        Product existingProduct = new Product(productId, "Product 1", "Description 1", new BigDecimal("10.00"), LocalDateTime.now(), LocalDateTime.now(), null);
        Product updatedProduct = new Product(productId, "Updated Product", "Updated Description", new BigDecimal("20.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);


        var result = productService.updateProduct(productId, updatedProduct);


        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Updated Product", result.getName());
    }

    @Test
    void testDeleteProduct() {

        Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", new BigDecimal("10.00"), LocalDateTime.now(), LocalDateTime.now(), null);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));


        assertDoesNotThrow(() -> productService.deleteProduct(productId));
    }
}
