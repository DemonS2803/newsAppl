package com.example.repositories;

import com.example.entities.Category;
import com.example.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    News findNewsById(Long newsId);
    List<News> findNewsByTitle(String title);
    List<News> findNewsByCategory(Category category);
    List<News> findAll();

}
