package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Emprunt;
import org.example.Model.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntRepository {

    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public Boolean saveEmprunt(Emprunt emprunt, List<Long> ids) {
            String sql = "INSERT INTO Emprunt (startDate, endDate, returne, emprunteur_id) VALUES (?, ?, ?, ?)";
            String insertLivreEmpruntSql = "INSERT INTO LivreEmprunt (livre_id, emprunt_id) VALUES (?, ?)";

            try (PreparedStatement empruntPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement livreEmpruntPreparedStatement = connection.prepareStatement(insertLivreEmpruntSql)) {
                empruntPreparedStatement.setDate(1, (Date) emprunt.getStartDate());
                empruntPreparedStatement.setDate(2, (Date) emprunt.getEndDate());
                empruntPreparedStatement.setBoolean(3, emprunt.getReturne());
                empruntPreparedStatement.setInt(4, Math.toIntExact(emprunt.getEmprunteurid()));
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

    public boolean update(Boolean returne,Long id)throws SQLException{
        String UpdateEmpruntQuery="Update Emprunt Set returne=? Where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(UpdateEmpruntQuery)){
            preparedStatement.setBoolean(1,returne);
            preparedStatement.setLong(2,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public boolean delete(Long id)throws SQLException{

        String deleteEmpruntQuery="Delete From Emprunt Where id=?";
        String deleteLivreEmprunt="Delete from LivreEmprunt where emprunt_id=(Select id from Emprunt Where id=?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteEmpruntQuery);PreparedStatement preparedStatementS = connection.prepareStatement(deleteLivreEmprunt)){
            preparedStatement.setLong(1,id);
            preparedStatementS.setLong(1,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public Emprunt findById(Long id)throws SQLException{
        List<Livre> livres=new ArrayList<>();
        String findByIdLivresQuery="SELECT l.* , C.*\n" +
                "FROM emprunt e\n" +
                "JOIN livreEmprunt el ON e.id = el.emprunt_id\n" +
                "JOIN livre l ON el.livre_id = l.id\n" +
                "JOIN Collection C On l.collection_id=C.id \n"+
                "WHERE e.id =?";
        String findByIdEmpruntQuery="Select * From Emprunt Where id=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(findByIdEmpruntQuery);PreparedStatement preparedStatementL=connection.prepareStatement(findByIdLivresQuery)){
            preparedStatement.setLong(1,id);
            preparedStatementL.setLong(1,id);

            ResultSet resultSet= preparedStatement.executeQuery();
            ResultSet resultSetL= preparedStatementL.executeQuery();
            if(resultSet.next()){
                Emprunt emprunt=new Emprunt();
                Livre livre=new Livre();
                emprunt.mapData(resultSet);
                while(resultSetL.next()){
                    livre.setId(resultSetL.getLong("id"));
                    livres.add(livre.mapData(resultSetL));
                }
                emprunt.setLivreList(livres);
                return emprunt;
            }
        }
        return null;
    }

    public List<Emprunt> findByAll()throws SQLException{
        List<Emprunt> emprunts=new ArrayList<>();
        List<Livre> livres=new ArrayList<>();
        String findByAllEmpruntQuery="Select * From Emprunt ";
        String findByIdLivresQuery="SELECT l.* , C.*\n" +
                "FROM emprunt e\n" +
                "JOIN livreEmprunt el ON e.id = el.emprunt_id\n" +
                "JOIN livre l ON el.livre_id = l.id\n" +
                "JOIN Collection C On l.collection_id=C.id \n"+
                "WHERE e.id =?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(findByAllEmpruntQuery);ResultSet resultSet= preparedStatement.executeQuery()){

            while(resultSet.next()){
                Emprunt emprunt=new Emprunt();

                emprunts.add(emprunt.mapData(resultSet));
                try(PreparedStatement preparedStatementL=connection.prepareStatement(findByIdLivresQuery)){
                    preparedStatement.setLong(1,resultSet.getLong("id"));
                    ResultSet resultSetL= preparedStatementL.executeQuery();
                    while(resultSetL.next()){
                        Livre livre=new Livre();
                        livre.setId(resultSetL.getLong("id"));
                        livres.add(livre.mapData(resultSetL));
                    }
                    emprunt.setLivreList(livres);
                }

            }
            return emprunts;
        }
    }

}
