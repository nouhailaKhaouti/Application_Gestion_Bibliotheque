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

        String insertCollectionQuery = "INSERT INTO Livre (numeroinventair,collection_id,status_id) VALUES (?,?,1)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCollectionQuery)) {
            preparedStatement.setString(1, livre.getNumeroInventair());
            preparedStatement.setLong(2, livre.getCollection().getId());
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
        String SelectLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectLivreQuery); ResultSet resultSet =preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Livre livre =new Livre();
                livres.add(livre.mapData(resultSet));
            }
        }
        return livres;
    }
    public Livre findByNI(String numero)throws SQLException{
        String IdLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id where l.numeroinventair=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IdLivreQuery)){
            preparedStatement.setString(1,numero);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Livre livre=new Livre();
                livre.setId(resultSet.getLong("id"));
                livre.mapData(resultSet);
                return livre;
            }
        }
        return null;
    }

    public List<Livre> findByStatus(String status)throws SQLException{
        List<Livre> livres=new ArrayList<>();
        String SelectLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id Where s.label=?";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectLivreQuery)){
            preparedStatement.setString(1,status);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()) {
                Livre livre =new Livre();
                livres.add(livre.mapData(resultSet));
            }
            return livres;
        }

    }
    public Livre findById(Long id)throws SQLException{
        String IdLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id where l.id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IdLivreQuery)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Livre livre=new Livre();
                livre.setId(resultSet.getLong("id"));
                livre.mapData(resultSet);
                return livre;
            }
        }
        return null;
    }

    public Livre findByTitre(String title)throws SQLException{
        String IdLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id where c.title=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IdLivreQuery)){
            preparedStatement.setString(1,title);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Livre livre=new Livre();
                livre.setId(resultSet.getLong("id"));
                livre.mapData(resultSet);
                return livre;
            }
        }
        return null;
    }

    public Livre findByAuthor(String author)throws SQLException{
        String IdLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id where c.auteur=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(IdLivreQuery)){
            preparedStatement.setString(1,author);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Livre livre=new Livre();
                livre.setId(resultSet.getLong("id"));
                livre.mapData(resultSet);
                return livre;
            }
        }
        return null;
    }



    public List<Livre> findByCollection(Long id)throws SQLException{
        List<Livre> livres=new ArrayList<>();

        String collectionLivreQuery="Select * from Livre l Join collection c on l.collection_id=c.id Join status s on l.status_id=s.id where id=?";
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

    public boolean change_status_perdue(){
        String sql="SELECT update_livre_status_perdue()";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
