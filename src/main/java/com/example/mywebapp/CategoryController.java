package com.example.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository repo;

    @GetMapping("/categories")
    public String listCategories(Model model){
        List<Category> listCategories = repo.findAll();
        model.addAttribute("listCategories",listCategories);
        return "categories";
    }

}
