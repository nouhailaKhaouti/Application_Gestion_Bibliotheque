package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Emprunt;

import java.sql.*;

public class EmpruntRepository {

    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public Emprunt saveEmprunt(Emprunt emprunt,int livreId) {
            String sql = "INSERT INTO Emprunt (startDate, endDate, returne, emprunteur_id) VALUES (?, ?, ?, ?)";
            String insertLivreEmpruntSql = "INSERT INTO LivreEmprunt (livre_id, emprunt_id) VALUES (?, ?)";

            try (PreparedStatement empruntPreparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement livreEmpruntPreparedStatement = connection.prepareStatement(insertLivreEmpruntSql)) {
                empruntPreparedStatement.setDate(1, (Date) emprunt.getStartDate());
                empruntPreparedStatement.setDate(2, (Date) emprunt.getEndDate());
                empruntPreparedStatement.setBoolean(3, emprunt.getReturne());
                empruntPreparedStatement.setInt(4, Math.toIntExact(emprunt.getEmprunteur()));
                empruntPreparedStatement.executeUpdate();
                int affectedRows = empruntPreparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    System.out.print("Creating Emprunt failed, no rows affected.");
                    return null;
                }

                try (var generatedKeys = empruntPreparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int empruntId = generatedKeys.getInt(1);

                        // Insert into LivreEmprunt to indicate which livre is being borrowed
                        livreEmpruntPreparedStatement.setInt(1, livreId);
                        livreEmpruntPreparedStatement.setInt(2, empruntId);
                        livreEmpruntPreparedStatement.executeUpdate();
                    } else {
                        System.out.print("Creating Emprunt failed, no ID obtained.");
                        return null;
                    }
                }
                return emprunt;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
