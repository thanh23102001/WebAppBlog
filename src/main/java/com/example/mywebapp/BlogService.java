package com.example.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository repo;

    @Autowired
    private IBlogRepository blogRepository;

 public List<Blog> listAll(){
     return repo.findAll();
 }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }
//    public void save(Blog blog){
//     repo.save(blog);
// }

    @Override
    public void remove(Long id) {
          blogRepository.deleteById(id);
    }

    public Blog get(Long id){
     return repo.findById(id).get();
 }
 public void delete(Long id){
     repo.deleteById(id);
 }

}

