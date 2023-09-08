package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Emprunteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurRepository {
    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public boolean save(Emprunteur emprunteur) throws SQLException {

        String insertEmprunteurQuery = "INSERT INTO Emprunteur (membreship, fullname, email, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertEmprunteurQuery)) {
            preparedStatement.setString(1, emprunteur.getMembreShip());
            preparedStatement.setString(2,emprunteur.getFullName());
            preparedStatement.setString(3, emprunteur.getEmail());
            preparedStatement.setString(4, emprunteur.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean update(Emprunteur emprunteur) throws SQLException {

        String updateEmprunteurQuery = "UPDATE Emprunteur Set membreship=?, fullname=?, email=?, phone=? WHERE id=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateEmprunteurQuery)) {
            preparedStatement.setString(1, emprunteur.getMembreShip());
            preparedStatement.setString(2,emprunteur.getFullName());
            preparedStatement.setString(3, emprunteur.getEmail());
            preparedStatement.setString(4, emprunteur.getPhone());
            preparedStatement.setLong(5, emprunteur.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean delete(Long id)throws SQLException{

        String deleteEmprunteurQuery="Delete From Emprunteur Where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteEmprunteurQuery)){
            preparedStatement.setLong(1,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public List<Emprunteur> findAll() throws SQLException{

        List<Emprunteur> emprunteurs=new ArrayList<>();
        String SelectEmprunteurQuery="Select * from Emprunteur";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectEmprunteurQuery); ResultSet resultSet =preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Emprunteur emprunteur =new Emprunteur();
                emprunteur.setId(resultSet.getLong("id"));
                emprunteurs.add(emprunteur.mapData(resultSet));
            }
        }
        return emprunteurs;
    }

    public Emprunteur findByMembreShip(String MembreShip)throws SQLException{
        String MembreShipEmprunteurQuery="Select * from Emprunteur where membreship=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(MembreShipEmprunteurQuery)){
            preparedStatement.setString(1,MembreShip);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Emprunteur emprunteur=new Emprunteur();
                emprunteur.setId(resultSet.getLong("id"));
                emprunteur.mapData(resultSet);
                return emprunteur;
            }
        }
        return null;
    }

    public Emprunteur findById(Long id)throws SQLException{
        String MembreShipEmprunteurQuery="Select * from Emprunteur where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(MembreShipEmprunteurQuery)){
            preparedStatement.setLong(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Emprunteur emprunteur=new Emprunteur();
                emprunteur.setId(resultSet.getLong("id"));
                emprunteur.mapData(resultSet);
                return emprunteur;
            }
        }
        return null;
    }

    private int emprunteurExists(String membreship) {
        String checkEmprunteurQuery = "SELECT COUNT(*) FROM emprunteur WHERE membreship = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkEmprunteurQuery)) {
            pstmt.setString(1, membreship);

            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
