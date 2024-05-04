package com.example;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1", "Description 1", new ArrayList<>()));
        categories.add(new Category(2L, "Category 2", "Description 2", new ArrayList<>()));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories.size(), result.size());
        for (int i = 0; i < categories.size(); i++) {
            assertEquals(categories.get(i).getCategory_id(), result.get(i).getCategory_id());
            assertEquals(categories.get(i).getName(), result.get(i).getName());
            assertEquals(categories.get(i).getDescription(), result.get(i).getDescription());
        }
    }


}
