package com.example.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private BlogService service;

    @Autowired
    private IBlogService blogService;

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Blog> listBlogs = service.listAll();
        model.addAttribute("listBlogs",listBlogs);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewBlogForm(Model model){
         Blog blog = new Blog();
         List<Category> listCategories = categoryRepo.findAll();
         model.addAttribute("blog",blog);
         model.addAttribute("listCategories", listCategories);
         return "new_blog";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute  BlogForm blog) {
        Blog blog1 = new Blog.BlogBuilder(blog.getTitle())
                .cover(blog.getCover()).content(blog.getContent()).build();
//        MultipartFile multipartFile = blog.getImage();
//        String fileName = multipartFile.getOriginalFilename();
//             try {
//                 FileCopyUtils.copy(blog.getImage().getBytes(), new File(this.fileUpload + fileName));
//             }catch (IOException e){
//                 e.printStackTrace();
//             }
//             blog1.setImage(fileName);
//             blogService.save(blog1);
         for(MultipartFile multipartFile : blog.getImage()){
             try {
                 var imageName = multipartFile.getOriginalFilename();
                 var is = multipartFile.getInputStream();
                 Files.copy(is, Paths.get(this.fileUpload + imageName), StandardCopyOption.REPLACE_EXISTING);

                 blogService.save(blog1);

             }catch (IOException e){
                 e.printStackTrace();
             }
         }
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
