package htw.webtech.WebTech.Projekt1.model;

import jakarta.persistence.*;


@Entity
public class Article {

    private String title;
    @Column(columnDefinition = "TEXT")
    private String inhalt;
    private boolean read;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Article(){

    }
    public Article(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getInhalt(){
        return inhalt;
    }
    public void setInhalt(String inhalt){
        this.inhalt = inhalt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read){
            this.read = read;
        }
    }

