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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Description 1", new BigDecimal("10.00"), LocalDateTime.now(), LocalDateTime.now(), null));
        products.add(new Product(2L, "Product 2", "Description 2", new BigDecimal("20.00"), LocalDateTime.now(), LocalDateTime.now(), null));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(products.size(), result.size());
        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).getId(), result.get(i).getId());
            assertEquals(products.get(i).getName(), result.get(i).getName());
            assertEquals(products.get(i).getDescription(), result.get(i).getDescription());
            assertEquals(products.get(i).getPrice(), result.get(i).getPrice());
        }
    }

}
