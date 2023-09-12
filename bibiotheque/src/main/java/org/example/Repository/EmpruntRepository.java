package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Model.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntRepository {

    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public boolean saveEmprunt(Emprunt emprunt, List<Long> ids) {
            String sql = "INSERT INTO Emprunt (startDate, endDate, returne, emprunteur_id) VALUES (?, ?, ?, ?)";
            String insertLivreEmpruntSql = "INSERT INTO LivreEmprunt (livre_id, emprunt_id) VALUES (?, ?)";
             Date Start=new Date(emprunt.getStartDate().getTime());
             Date End=new Date(emprunt.getEndDate().getTime());
            try (PreparedStatement empruntPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement livreEmpruntPreparedStatement = connection.prepareStatement(insertLivreEmpruntSql)) {
                empruntPreparedStatement.setDate(1, Start);
                empruntPreparedStatement.setDate(2, End);
                empruntPreparedStatement.setBoolean(3, emprunt.getReturne());
                empruntPreparedStatement.setLong(4, emprunt.getEmprunteurid());
                empruntPreparedStatement.executeUpdate();
                int affectedRows = empruntPreparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    return false;
                }
                try (var generatedKeys = empruntPreparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int empruntId = generatedKeys.getInt(1);
                        for(Long id : ids) {
                            livreEmpruntPreparedStatement.setLong(1, id);
                            livreEmpruntPreparedStatement.setInt(2, empruntId);
                            livreEmpruntPreparedStatement.executeUpdate();
                        }
                    } else {
                        return false;
                    }
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    public boolean update(Long id)throws SQLException{
        String UpdateEmpruntQuery="Update Emprunt Set returne=? Where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(UpdateEmpruntQuery)){
            preparedStatement.setBoolean(1,true);
            preparedStatement.setLong(2,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public boolean delete(Long id)throws SQLException{

        String deleteLivreEmprunt="Delete from LivreEmprunt where livre_id=?";
        try( PreparedStatement preparedStatementS = connection.prepareStatement(deleteLivreEmprunt)){
            preparedStatementS.setLong(1,id);

            int rowDeleted=preparedStatementS.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public Emprunt findById(Long id)throws SQLException{
        List<Livre> livres=new ArrayList<>();
        String findByIdEmpruntQuery="Select em.*,E.fullname,E.membreship,E.email,E.phone  From Emprunt em join emprunteur E ON em.emprunteur_id=E.id where em.id=? ";
        String findByIdLivresQuery="SELECT L.*,C.*,S.*,LE.* FROM LivreEmprunt LE  JOIN Livre L ON LE.livre_id = L.id  JOIN Collection C ON L.collection_id = C.id  JOIN Status S ON L.status_id = S.id where LE.emprunt_id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(findByIdEmpruntQuery);PreparedStatement preparedStatementL=connection.prepareStatement(findByIdLivresQuery)){
            preparedStatement.setLong(1,id);
            preparedStatementL.setLong(1,id);

            ResultSet resultSet= preparedStatement.executeQuery();
            ResultSet resultSetL= preparedStatementL.executeQuery();
            if(resultSet.next()){
                Emprunt emprunt=new Emprunt();
                Livre livre=new Livre();
                emprunt.mapData(resultSet);
                Emprunteur emprunteur = new Emprunteur();
                emprunteur.setId(resultSet.getLong("emprunteur_id"));
                emprunteur.mapData(resultSet);
                emprunt.setEmprunteur(emprunteur);
                while(resultSetL.next()){
                    livre.setId(resultSetL.getLong("livre_id"));
                    livres.add(livre.mapData(resultSetL));
                }
                emprunt.setLivreList(livres);
                return emprunt;
            }
        }
        return null;
    }

    public List<Emprunt> findByAll() throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String findByAllEmpruntQuery = "SELECT em.*, E.fullname, E.membreship, E.email, E.phone " +
                "FROM Emprunt em " +
                "JOIN Emprunteur E ON em.emprunteur_id = E.id";
        String findByIdLivresQuery = "SELECT L.*, C.*, S.*, LE.* " +
                "FROM LivreEmprunt LE " +
                "JOIN Livre L ON LE.livre_id = L.id " +
                "JOIN Collection C ON L.collection_id = C.id " +
                "JOIN Status S ON L.status_id = S.id " +
                "WHERE LE.emprunt_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByAllEmpruntQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Emprunt emprunt = new Emprunt();

                Emprunteur emprunteur = new Emprunteur();
                emprunteur.setId(resultSet.getLong("emprunteur_id"));
                emprunteur.mapData(resultSet);

                List<Livre> livres = new ArrayList<>();
                try (PreparedStatement preparedStatementL = connection.prepareStatement(findByIdLivresQuery)) {
                    preparedStatementL.setLong(1, resultSet.getLong("id"));
                    ResultSet resultSetL = preparedStatementL.executeQuery();

                    while (resultSetL.next()) {
                        Livre livre = new Livre();
                        livre.setId(resultSetL.getLong("livre_id"));
                        livres.add(livre.mapData(resultSetL));
                    }
                    emprunt.setEmprunteur(emprunteur);
                    emprunt.setLivreList(livres);
                    emprunts.add(emprunt.mapData(resultSet));
                }

            }
        }
        return emprunts;
    }

    public Integer checkCount(Long id){
        String sql="Select count(*) from livreemprunt where emprunt_id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Emprunt findEmpruntLivre(Livre livre){
        String sql="Select e.* from emprunt e join livreemprunt le on e.id=le.emprunt_id join livre l on le.livre_id=l.id where l.id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,livre.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Emprunt emprunt=new Emprunt();
                emprunt.mapData(resultSet);
                return emprunt;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Integer findByEmprunteur(Emprunt emprunt){
        String sql="Select count(*) As count FROM emprunt where emprunteur_id=? And returne=false";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,emprunt.getEmprunteur().getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("count");
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
