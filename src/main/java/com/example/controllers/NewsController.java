package com.example.controllers;

import com.example.dto.NewsDTO;
import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

//    @Autowired
//    public NewsController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping("")
    public String newsView(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "news";
    }

//    @PostMapping("")
//    public String customNewsView(@RequestParam(name = "title") String title,
//                                 @RequestParam(name = "category") Category category,
//                                 Model model) {
//
//    }
//
//    @GetMapping("/edit")
//    public String showEditNews(Model model) {
//        return "news-edit";
//    }
//
//    @PostMapping("/edit")
//    public String saveEditNews(NewsDTO newsDTO, Model model) {
//
//    }
}
