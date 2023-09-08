package org.example.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Livre {
    private Long id;
    private String numeroInventair;

    private Collection collection;
    private Status status;

    private List<Emprunt> empruntList;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Livre(String numeroInventair, Status status,Collection collection) {
        this.collection=collection;
        this.numeroInventair = numeroInventair;
        this.status = status;
    }

    public Livre() {
    }

    public List<Emprunt> getEmpruntList() {
        return empruntList;
    }

    public void setEmpruntList(List<Emprunt> empruntList) {
        this.empruntList = empruntList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroInventair() {
        return numeroInventair;
    }

    public void setNumeroInventair(String numeroInventair) {
        this.numeroInventair = numeroInventair;
    }

    public Livre mapData(ResultSet resultSet) throws SQLException {

        Collection collection=new Collection();
        this.id = resultSet.getLong("id");
        this.numeroInventair = resultSet.getString("numeroInventair");
        this.collection=collection.mapData(resultSet);
        return this;
    }
}
