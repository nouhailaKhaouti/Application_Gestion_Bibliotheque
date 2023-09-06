package org.example.Controller;

import org.example.Config.DatabaseConnection;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class AuthentificationController {

    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);

    public Boolean Authenification(){
    User test=new User();
    Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
    String enteredUsername = scanner.nextLine();
        test.setUsername(enteredUsername);
        System.out.print("Enter password: ");
    String enteredPassword = scanner.nextLine();
        test.setPassword(enteredPassword);
    User user =userService.authenticate(test);
        if (user!=null) {
        System.out.print( "Authentication successful. Welcome, " + user.getUsername() + "!");
        return true;
    } else {
            System.out.print(  "Authentication failed. Invalid username or password.");
            return false;
    }
    }
}
