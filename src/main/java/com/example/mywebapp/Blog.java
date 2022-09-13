package com.example.mywebapp;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

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
//    private String image;

    @Transient
    private List<MultipartFile> image;
    public List<MultipartFile> getImage() {
        return image;
    }

    public void setImage(List<MultipartFile> image) {
        this.image = image;
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog() {
    }

    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.cover = blogBuilder.cover;
        this.content = blogBuilder.content;
//        this.image = blogBuilder.image;
        this.category = blogBuilder.category;
    }

//
    public static class BlogBuilder{
        private final String title;
        private String cover;
//        private String image;

        private  String content;

        private Category category;

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

//        public BlogBuilder image(String image) {
//            this.image = image;
//            return this;
//        }

        public BlogBuilder category(Category category){
            this.category = category;
            return this;
        }



        public Blog build() {
            return new Blog(this);
        }

    }
}
