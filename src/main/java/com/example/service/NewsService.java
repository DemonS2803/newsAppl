package com.example.service;

import com.example.dto.NewsDTO;
import com.example.entities.Category;
import com.example.entities.News;
import com.example.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public boolean save(NewsDTO newsDTO) {
        try {
            News news = News.builder()
                    .title(newsDTO.getTitle())
                    .content(newsDTO.getContent())
                    .category(newsDTO.getCategory())
                    .views(0L)
                    .build();
            newsRepository.save(news);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error during creating a new News");
        }
    }
}
