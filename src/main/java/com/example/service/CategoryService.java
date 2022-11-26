package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }


    public boolean save(CategoryDTO categoryDTO) {
        try {
            Category category = Category.builder()
                    .name(categoryDTO.getName())
                    .build();
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error during creating a new Category");
        }
    }
}
