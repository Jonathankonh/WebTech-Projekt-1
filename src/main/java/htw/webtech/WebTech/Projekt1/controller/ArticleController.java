package htw.webtech.WebTech.Projekt1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import htw.webtech.WebTech.Projekt1.model.Article;
import java.util.List;


@RestController
public class ArticleController {


    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        return List.of(
                new Article("ETF Grundlagen"),
                new Article("Aktien Basics"),
                new Article("Diversifikation")
        );
    }

}
