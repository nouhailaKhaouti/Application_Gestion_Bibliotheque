package org.example.Repository;

import org.example.Config.DatabaseConnection;
import org.example.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository {
    DatabaseConnection db=new DatabaseConnection();
    Connection connection = db.connect();

    public User findByUsername(User userC) {
        try {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userC.getUsername());
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    User user = new User();
                    user.setUsername(result.getString("username"));
                    user.setPassword(result.getString("password"));
                    return user;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return null;
    }
}
