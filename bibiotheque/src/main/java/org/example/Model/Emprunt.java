package org.example.Model;

import java.util.Date;
import java.util.List;

public class Emprunt {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Boolean returne;
    private List<Livre> livreList;
    private Emprunteur emprunteur;

    public Emprunt(Date startDate, Date endDate, Boolean returne, List<Livre> livreList, Emprunteur emprunteur) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.returne = returne;
        this.livreList = livreList;
        this.emprunteur = emprunteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getReturne() {
        return returne;
    }

    public void setReturne(Boolean returne) {
        this.returne = returne;
    }

    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }
}
