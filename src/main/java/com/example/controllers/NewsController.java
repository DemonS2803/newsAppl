package com.example.controllers;

import com.example.dto.NewsDTO;
import com.example.entities.Category;
import com.example.entities.News;
import com.example.repositories.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final CategoryService categoryService;

    @GetMapping("")
    public String newsView(Model model) {
        model.addAttribute("news", newsService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        System.out.println(categoryService.findAll().size());
        return "news";
    }

    @PostMapping("")
    public String customNewsView(String title,
                                 Category category,
                                 Model model) {
        System.out.println(title);
        System.out.println(category);
        System.out.println(newsService.findNewsByParams(title, category));
        model.addAttribute("news", newsService.findNewsByParams(title, category));
        model.addAttribute("categories", categoryService.findAll());
        return "news";
    }

    @GetMapping("/{id}")
    public String newsView(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.createDTObyId(id));
        return "news-view";
    }

    @GetMapping("/new")
    public String newNews(Model model) {
        model.addAttribute("news", new NewsDTO());
        model.addAttribute("categories", categoryService.findAll());
        return "news-new";
    }

    @PostMapping("/new")
    public String saveNewNews(NewsDTO newsDTO, Model model) {
        if (newsService.save(newsDTO)) {
            return "redirect:/";
        } else {
            model.addAttribute("news", newsDTO);
            return "news-new";
        }

    }

    @GetMapping("/edit/{id}")
    public String editNews(@PathVariable Long id, Model model) {
        News news = newsService.findNewsById(id);
        model.addAttribute("oldNews", news);
        model.addAttribute("newNews", new NewsDTO());
        return "news-edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditNews(@PathVariable Long id, NewsDTO newsDTO, Model model) {
        if (newsService.saveEdited(newsDTO)) {
            return "redirect:/news/" + id;
        } else {
            model.addAttribute("newNews", newsDTO);
            return "news-edit";
        }

    }

}
