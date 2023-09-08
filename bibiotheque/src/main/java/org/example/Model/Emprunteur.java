package org.example.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Emprunteur {
    private Long id;
    private String membreShip;
    private String fullName;
    private String email;
    private String phone ;
    private List<Emprunt> empruntList;

    public Emprunteur(String membreShip, String fullName, String email, String phone) {
        this.membreShip = membreShip;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Emprunteur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMembreShip() {
        return membreShip;
    }

    public void setMembreShip(String membreShip) {
        this.membreShip = membreShip;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Emprunt> getEmpruntList() {
        return empruntList;
    }

    public void setEmpruntList(List<Emprunt> empruntList) {
        this.empruntList = empruntList;
    }

    public Emprunteur mapData(ResultSet resultSet) throws SQLException {

        this.id = resultSet.getLong("id");
        this.membreShip = resultSet.getString("membreShip");
        this.fullName = resultSet.getString("fullName");
        this.email = resultSet.getString("email");
        this.phone = resultSet.getString("phone");

        return this;
    }
}
