package com.example.mywebapp;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BlogForm {
   private Long id;
   private String title;
   private String cover;
   private String content;
//   private MultipartFile image;

    private List<MultipartFile> image;

   private Category category;

   public BlogForm(){

   }

   public BlogForm(BlogFormBuilder blogFormBuilder){
       this.title = blogFormBuilder.title;
       this.cover = blogFormBuilder.cover;
       this.content= blogFormBuilder.content;
//       this.image = blogFormBuilder.image;
       this.category = blogFormBuilder.category;
   }


   public static class BlogFormBuilder{
       private final String title;
       private String cover;
       private String content;
//       private MultipartFile image;


       private Category category;

       public BlogFormBuilder(String title){
           this.title = title;
       }

       public BlogFormBuilder cover(String cover){
           this.cover = cover;
           return this;
       }

       public BlogFormBuilder content(String content){
           this.content = content;
           return this;
       }

//       public BlogFormBuilder image(MultipartFile image) {
//           this.image = image;
//           return this;
//       }

       public BlogFormBuilder category(Category category) {
           this.category = category;
           return this;
       }

       public BlogForm build() {
           return new BlogForm(this);
       }
   }

}
