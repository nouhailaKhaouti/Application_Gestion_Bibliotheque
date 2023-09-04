package org.example.Model;

import java.util.List;

public class Status {
    private Long id;
    private String label;
    private List<Livre> livreList;

    public Status(String label) {
        this.label = label;
    }

    public Status() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
