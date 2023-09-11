package org.example;
import org.example.Controller.AuthentificationController;
import org.example.Controller.EmpruntController;
import org.example.Controller.MenuController;
import org.example.Model.Status;

import java.sql.SQLException;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void main(String[] args) throws SQLException {
        AuthentificationController authentificationController=new AuthentificationController();
        MenuController menuController=new MenuController();
        Boolean auth=authentificationController.Authenification();
        if (auth) {
             menuController.displayMenu();
        } else {
            System.out.println("Access denied. Please authenticate first.");
        }
    }
}