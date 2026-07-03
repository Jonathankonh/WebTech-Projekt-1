package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.Article;
import htw.webtech.WebTech.Projekt1.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        logger.info("Getting all articles");
        return articleService.getAllArticles();
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article article){
        logger.info("Creating article {}", article);
        return articleService.saveArticle(article);
    }

    @PutMapping("/articles/{id}")
    public Article putArticle(@PathVariable Long id, @RequestBody Article article){
        logger.info("update Article with id: " + id);
        return articleService.updateArticle(article, id);
    }

    @DeleteMapping("/articles/{id}")
    public void  deleteArticle(@PathVariable long id) {
        logger.info("Delete article with id " + id);
        articleService.deleteArticle(id);
    }
}