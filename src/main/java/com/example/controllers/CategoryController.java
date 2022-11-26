package com.example.controllers;

import com.example.dto.CategoryDTO;
import com.example.repositories.CategoryRepository;
import com.example.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("category", new CategoryDTO());
        return "category-new";
    }

    @PostMapping("/new")
    public String saveNewCategory(CategoryDTO categoryDTO, Model model) {
        if (categoryService.save(categoryDTO)) {
            return "redirect:/categories";
        } else {
            model.addAttribute("category", categoryDTO);
            return "category-new";
        }

    }
}
