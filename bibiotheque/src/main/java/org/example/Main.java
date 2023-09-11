package org.example;
import org.example.Controller.AuthentificationController;
import org.example.Controller.MenuController;

import java.sql.SQLException;


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