package com.example.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private BlogService service;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Blog> listBlogs = service.listAll();
        model.addAttribute("listBlogs",listBlogs);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewBlogForm(Model model){
         Blog blog = new Blog();
         model.addAttribute("blog",blog);
         return "new_blog";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("blog") Blog blog){
      service.save(blog);
      return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView  showEditBlogForm(@PathVariable(name="id")Long id){
     ModelAndView mav =new ModelAndView("edit_blog");
        Blog blog = service.get(id);
        mav.addObject("blog",blog);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBlog(@PathVariable(name="id")Long id){
        service.delete(id);
        return "redirect:/";
    }
}
