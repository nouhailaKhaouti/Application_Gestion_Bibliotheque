package org.example;
import org.example.Config.DatabaseConnection;
import org.example.Model.Status;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;

import java.sql.Connection;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void main(String[] args) {
        DatabaseConnection db=new DatabaseConnection();
        Connection connection = db.connect();
        UserRepository userRepository = new UserRepository(connection);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();
        Boolean authentification =userService.authenticate(enteredUsername, enteredPassword);
        if (authentification) {
            System.out.println("Authentication successful. Welcome, " + enteredUsername + "!");
        } else {
            System.out.println("Authentication failed. Invalid username or password.");
        }
        scanner.close();
        if (authentification) {
            Status status=new Status("disponible");
            System.out.print(status.getLabel());
        } else {
            // Deny access or display a message
            System.out.println("Access denied. Please authenticate first.");
        }
    }
}