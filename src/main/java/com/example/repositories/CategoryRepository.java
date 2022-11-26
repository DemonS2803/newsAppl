package com.example.repositories;

import com.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);
    Category findCategoryById(Long id);
    List<Category> findAll();

}
