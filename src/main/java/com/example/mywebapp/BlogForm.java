package com.example.mywebapp;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogForm {
    private Long id;
    private String title;
    private String cover;
    private String content;
    private MultipartFile image;

    public BlogForm() {

    }

    public BlogForm(BlogFormBuilder blogFormBuilder) {
        this.title = blogFormBuilder.title;
        this.cover = blogFormBuilder.cover;
        this.content = blogFormBuilder.content;
        this.image = blogFormBuilder.image;
    }


    public static class BlogFormBuilder {
        private final String title;
        private String cover;
        private String content;
        private MultipartFile image;


        public BlogFormBuilder(String title) {
            this.title = title;
        }

        public BlogFormBuilder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public BlogFormBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogFormBuilder image(MultipartFile image) {
            this.image = image;
            return this;
        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }
}