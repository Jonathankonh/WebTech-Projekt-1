package htw.webtech.WebTech.Projekt1.service;

import htw.webtech.WebTech.Projekt1.model.Article;
import htw.webtech.WebTech.Projekt1.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(long id) {
        return articleRepository.findById(id);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }


    public Article updateArticle(Article article, Long id) {
        Optional<Article> articles = articleRepository.findById(id);

        if(articles.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
        Article beitraege = articles.get();

        beitraege.setRead(article.isRead());

        return articleRepository.save(beitraege);
    }

    public void deleteArticle(long id) {
        if(articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article nicht gefunden");
        }
    }
}
