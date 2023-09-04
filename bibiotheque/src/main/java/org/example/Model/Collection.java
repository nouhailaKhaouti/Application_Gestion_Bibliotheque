package org.example.Model;

import java.util.List;

public class Collection {
    private Long id;
    private String isbn;
    private String title;
    private Integer totale;
    private String auteur;

    private List<Livre> livres;

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public Collection(String isbn, String title, Integer totale, String auteur) {
        this.isbn = isbn;
        this.title = title;
        this.totale = totale;
        this.auteur = auteur;
    }

    public Collection() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotale(Integer totale) {
        this.totale = totale;
    }

    public void setAuteur(String auteur) {
        auteur = auteur;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTotale() {
        return totale;
    }

    public String getAuteur() {
        return auteur;
    }
}
