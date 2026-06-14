package htw.webtech.WebTech.Projekt1.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import htw.webtech.WebTech.Projekt1.model.Article;
import htw.webtech.WebTech.Projekt1.repository.ArticleRepository;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article article){
        return articleRepository.save(article);
    }
}