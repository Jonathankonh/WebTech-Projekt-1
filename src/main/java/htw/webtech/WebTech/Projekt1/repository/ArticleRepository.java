package htw.webtech.WebTech.Projekt1.repository;

import htw.webtech.WebTech.Projekt1.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
