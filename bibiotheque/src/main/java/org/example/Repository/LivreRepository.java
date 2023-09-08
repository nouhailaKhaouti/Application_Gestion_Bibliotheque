package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Collection;
import org.example.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreRepository {
    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public boolean save(Livre livre) throws SQLException {

        String insertCollectionQuery = "INSERT INTO Collection (isbn, title, auteur, totale) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCollectionQuery)) {
            preparedStatement.setString(1, livre.getNumeroInventair());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean update(Livre livre) throws SQLException {

        String updateCollectionQuery = "UPDATE Livre Set numeroInventair=? WHERE id=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCollectionQuery)) {
            preparedStatement.setString(1, livre.getNumeroInventair());
            preparedStatement.setLong(2, livre.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean delete(Long id)throws SQLException{

        String deleteCollectionQuery="Delete From Livre Where id=?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteCollectionQuery)){
            preparedStatement.setLong(1,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }
    public int livreExists(String numeroIn) {
        String checkLivreQuery = "SELECT COUNT(*) FROM livre WHERE numeroinventair = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkLivreQuery)) {
            pstmt.setString(1, numeroIn);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public List<Livre> findAll() throws SQLException{

        List<Livre> livres=new ArrayList<>();
        String SelectLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectLivreQuery); ResultSet resultSet =preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Livre livre =new Livre();
                livres.add(livre.mapData(resultSet));
            }
        }
        return livres;
    }
    public Livre findById(Long id)throws SQLException{
        String IdLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IdLivreQuery)){
            preparedStatement.setLong(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Livre livre=new Livre();
                livre.mapData(resultSet);
                return livre;
            }
        }
        return null;
    }



    public List<Livre> findByCollection(Long id)throws SQLException{
        List<Livre> livres=new ArrayList<>();

        String collectionLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(collectionLivreQuery)){
            preparedStatement.setLong(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Livre livre =new Livre();
                    livres.add(livre.mapData(resultSet));
                }
                return livres;
        }
    }
}
