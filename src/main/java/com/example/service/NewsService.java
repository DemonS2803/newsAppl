package com.example.service;

import com.example.dto.NewsDTO;
import com.example.entities.Category;
import com.example.entities.News;
import com.example.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class NewsService {
    private NewsRepository newsRepository;
    private CategoryService categoryService;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News findNewsById(Long id) {
        return newsRepository.findNewsById(id);
    }

    public List<News> findNewsByCategory(Category category) {
        return newsRepository.findNewsByCategory(category);
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

    public boolean saveEdited(NewsDTO newsDTO) {
        try {
            News news = findNewsById(newsDTO.getId());
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());
            news.setCategory(newsDTO.getCategory());
            newsRepository.save(news);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error during saving edited News");
        }
    }

    public List<News> findNewsByParams(String title, Category category) {
        List<News> newsList;
        if (!Objects.equals(category, null)) {
            newsList = findNewsByCategory(category);
        } else {
            newsList = findAll();
        }
        if (!Objects.equals(title, null)) {
            for (int i = 0; i < newsList.size(); i++) {
                if (!newsList.get(i).getTitle().contains(title)) {
                    newsList.remove(i);
                    i--;
                }
            }
        }
        return newsList;
//        return newsRepository.findNewsByCategory(category);

    }

    public NewsDTO createDTObyId(Long id) {
        News news = findNewsById(id);
        news.setViews(news.getViews() + 1);
        NewsDTO newsDTO = NewsDTO.builder()
                .id(id)
                .title(news.getTitle())
                .content(news.getContent())
                .category(categoryService.findCategoryById(1L))
                .build();
        newsRepository.save(news);
        return newsDTO;

    }
}
