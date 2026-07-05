package htw.webtech.WebTech.Projekt1.controller;

import htw.webtech.WebTech.Projekt1.model.Article;
import htw.webtech.WebTech.Projekt1.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();

        var article1 = new Article("Test Artikel 1");
        article1.setInhalt("Das ist der Inhalt");
        article1.setRead(false);
        articleRepository.save(article1);

        var article2 = new Article("Test Artikel 2");
        article2.setInhalt("Weiterer Inhalt");
        article2.setRead(true);
        articleRepository.save(article2);
    }

    @Test
    @DisplayName("should return all articles")
    void should_return_all_articles() throws Exception {
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test Artikel 1"))
                .andExpect(jsonPath("$[1].title").value("Test Artikel 2"));
    }

    @Test
    @DisplayName("should create a new article")
    void should_create_a_new_article() throws Exception {
        String articleJson = "{\"title\": \"Neuer Artikel\", \"inhalt\": \"Der Inhalt\"}";

        mockMvc.perform(
                        post("/articles")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Neuer Artikel"));
    }

    @Test
    @DisplayName("should delete article")
    void should_delete_article() throws Exception {

        var article = new Article("Test Artikel ");
        article.setInhalt("Der Inhalt");
        var savedArticle = articleRepository.save(article);


        mockMvc.perform(
                delete("/articles/" + savedArticle.getId())
        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should throw an exception when id is not found")
    void should_return_404() throws Exception {
        mockMvc.perform(
                delete("/articles/999991"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should throw 404 when article is not found")
    void should_return_404_put() throws Exception {
        String updateArticleJson = "{\"title\": \"...\", \"inhalt\": \"...\", \"read\": false}";
        mockMvc.perform(
                put("/articles/999991")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateArticleJson)
        )
        .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("should update article")
    void should_update_article() throws Exception {
        var article = new Article("Test Artikel 3");
        article.setInhalt("Der Inhalt");
        var savedArticle = articleRepository.save(article);

        String updatedArticleJson = "{\"title\": \"Neuer Artikel 3\",  \"inhalt\": \"Der Inhalt\", \"read\": true}";
        mockMvc.perform(
                put("/articles/" + savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedArticleJson)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.read").value(true));
    }
}