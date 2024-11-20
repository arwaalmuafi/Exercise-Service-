package com.example.exerciseservice.Controller;

import com.example.exerciseservice.ApiReaspones.ApiReaspones;
import com.example.exerciseservice.Model.NewsArticle;
import com.example.exerciseservice.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService articleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle() {
        ArrayList<NewsArticle> newsArticles = articleService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        articleService.addNewsArticles(newsArticle);
        return ResponseEntity.status(200).body(new ApiReaspones("news article added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticel(@PathVariable String id, @RequestBody @Valid NewsArticle newsarticle, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        boolean isUpdate = articleService.updateArticle(id, newsarticle);

        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiReaspones("article updated"));
        }

        return ResponseEntity.status(400).body(new ApiReaspones("Article not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticel(@PathVariable String id) {
        boolean isDeleted = articleService.deleteArticle(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("article deleted");
        }

        return ResponseEntity.status(400).body(new ApiReaspones("article not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishArticle(@PathVariable String id) {

        boolean isPublished = articleService.publishArticle(id);

        if (isPublished) {
            return ResponseEntity.status(200).body(new ApiReaspones("is published"));
        }
        return ResponseEntity.status(400).body(new ApiReaspones("not found"));
    }

    @GetMapping("/published")
    public ResponseEntity getAllPublishedArticles() {
        ArrayList<NewsArticle> publishedArticles = articleService.getAllPublished();
        if (publishedArticles.isEmpty()) {
            return ResponseEntity.status(200).body(publishedArticles);
        }
        return ResponseEntity.status(400).body(new ApiReaspones("no articles found"));
    }



     @GetMapping("/category/{Category}")
    public ResponseEntity NewsArticlesByCategory(@PathVariable String Category){
        ArrayList<NewsArticle>byCategory=articleService.getNewsArticles();
        if(byCategory.isEmpty()){
            return ResponseEntity.status(400).body(new ApiReaspones("not found"));
        }

        return ResponseEntity.status(200).body(byCategory);

    }




}




