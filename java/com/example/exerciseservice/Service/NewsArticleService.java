package com.example.exerciseservice.Service;

import com.example.exerciseservice.ApiReaspones.ApiReaspones;
import com.example.exerciseservice.Model.NewsArticle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public void addNewsArticles(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }

    public boolean updateArticle(String id, NewsArticle newsarticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public boolean deleteArticle(String id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.remove(i);
                return true;
            }
        }

        return false;
    }


    public boolean publishArticle(String id) {
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getId().equals(id) && !newsArticle.isPublished()) {
                newsArticle.setPublished(true);
                return true;
            }
        }
        return false;
    }


    public ArrayList<NewsArticle> getAllPublished() {
        ArrayList<NewsArticle> published = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.isPublished()) {
                published.add(newsArticle);
            }
        }
        return published;
    }

    public ArrayList<NewsArticle> NewsArticlesByCategory(String Category) {
        ArrayList<NewsArticle> byCategory = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if(newsArticle.getCategory().equalsIgnoreCase(Category))
                byCategory.add(newsArticle);
        }
        return byCategory;
    }



}