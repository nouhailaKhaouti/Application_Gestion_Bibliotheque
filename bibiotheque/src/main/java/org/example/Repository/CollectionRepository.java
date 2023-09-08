package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionRepository {
    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public boolean save(Collection collection) throws SQLException {

        String insertCollectionQuery = "INSERT INTO Collection (isbn, title, auteur, totale) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCollectionQuery)) {
            preparedStatement.setString(1, collection.getIsbn());
            preparedStatement.setString(2,collection.getTitle());
            preparedStatement.setString(3, collection.getAuteur());
            preparedStatement.setInt(4, collection.getTotale());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean update(Collection collection) throws SQLException {

        String updateCollectionQuery = "UPDATE Collection Set isbn=?, title=?, auteur=?, totale=? WHERE id=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCollectionQuery)) {
            preparedStatement.setString(1, collection.getIsbn());
            preparedStatement.setString(2,collection.getTitle());
            preparedStatement.setString(3, collection.getAuteur());
            preparedStatement.setInt(4, collection.getTotale());
            preparedStatement.setLong(5, collection.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean delete(String isbn)throws SQLException{

        String deleteCollectionQuery="Delete From Collection Where isbn=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteCollectionQuery)){
            preparedStatement.setString(1,isbn);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public List<Collection> findAll() throws SQLException{

        List<Collection> collections=new ArrayList<>();
        String SelectCollectionQuery="Select * from Collection";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectCollectionQuery);ResultSet resultSet =preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Collection collection =new Collection();
                collections.add(collection.mapData(resultSet));
            }
        }
        return collections;
    }

    public Collection findByIsbn(String Isbn)throws SQLException{
        String IsbnCollectionQuery="Select * from Collection where isbn=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IsbnCollectionQuery)){
            preparedStatement.setString(1,Isbn);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Collection collection=new Collection();
                collection.mapData(resultSet);
                return collection;
            }
        }
        return null;
    }

    public Collection findById(Long id)throws SQLException{
        String IsbnCollectionQuery="Select * from Collection where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IsbnCollectionQuery)){
            preparedStatement.setLong(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Collection collection=new Collection();
                collection.mapData(resultSet);
                return collection;
            }
        }
        return null;
    }

    public int collectionExists(String isbn) {
        String checkCollectionQuery = "SELECT COUNT(*) FROM collection WHERE isbn = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkCollectionQuery)) {
            pstmt.setString(1, isbn);

            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
