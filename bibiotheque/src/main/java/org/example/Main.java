package org.example;
import org.example.Controller.AuthentificationController;
import org.example.Controller.LivreController;
import org.example.Controller.MenuController;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws SQLException {
        LivreController livreController=new LivreController();
        AuthentificationController authentificationController=new AuthentificationController();
        MenuController menuController=new MenuController();
        Boolean auth=authentificationController.Authenification();
        if (auth) {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable task = () -> {
                livreController.change_status_perdue();
                System.out.println("Function executed every 24 hours.");
            };

            long initialDelay = 0;
            long period = 24 * 60 * 60;

            scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
             menuController.displayMenu();
        } else {
            System.out.println("Access denied. Please authenticate first.");
        }
    }
}