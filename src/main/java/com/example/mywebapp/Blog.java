package com.example.mywebapp;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String cover;
    private String content;

    private String image;

    public Blog() {
    }

    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.cover = blogBuilder.cover;
        this.content = blogBuilder.content;
        this.image = blogBuilder.image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static class BlogBuilder{
        private final String title;
        private String cover;
        private String image;

        private  String content;

        public BlogBuilder(String title) {
            this.title = title;
        }

        public BlogBuilder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public BlogBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }

    }

}
