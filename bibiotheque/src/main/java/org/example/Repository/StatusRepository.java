package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();
    public boolean save(Status status) throws SQLException {

        String insertCollectionQuery = "INSERT INTO Status (lable) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCollectionQuery)) {
            preparedStatement.setString(1,status.getLabel());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean update(Status status) throws SQLException {

        String updateCollectionQuery = "UPDATE Status Set label=? WHERE id=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCollectionQuery)) {
            preparedStatement.setString(1, status.getLabel());
            preparedStatement.setLong(2, status.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean delete(Long id)throws SQLException{

        String deleteStatusQuery="Delete From Status Where id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteStatusQuery)){
            preparedStatement.setLong(1,id);

            int rowDeleted=preparedStatement.executeUpdate();
            return rowDeleted > 0;
        }
    }

    public List<Status> findAll() throws SQLException{

        List<Status> statusA=new ArrayList<>();
        String SelectStatusQuery="Select * from Status";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectStatusQuery); ResultSet resultSet =preparedStatement.executeQuery()){
            while (resultSet.next()) {
                Status status =new Status();
                statusA.add(status.mapData(resultSet));
            }
        }
        return statusA;
    }

    public Status findById(Long id) throws SQLException{

        String SelectStatusQuery="Select * from Status where id=?";

        try(PreparedStatement preparedStatement=connection.prepareStatement(SelectStatusQuery)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                Status status =new Status();
                status.mapData(resultSet);
                return status;
            }
        }
        return null;
    }

    public int statusExists(String label) {
        String checkStatusQuery = "SELECT COUNT(*) FROM collection WHERE label = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkStatusQuery)) {
            pstmt.setString(1, label);

            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
