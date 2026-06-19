package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.Article;
import htw.webtech.WebTech.Projekt1.controller.ArticleController;
import htw.webtech.WebTech.Projekt1.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/articles/{id}")
    public Article putArticle(@PathVariable Long id, @RequestBody Article article){
        Optional<Article> articles = articleRepository.findById(id);

        if(articles.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
        Article beitraege = articles.get();

        beitraege.setRead(article.isRead());

        return articleRepository.save(beitraege);
    }

    @DeleteMapping("/articles/{id}")
    public void  deleteArticle(@PathVariable long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artikel nicht gefunden. ");
        }
    }
}