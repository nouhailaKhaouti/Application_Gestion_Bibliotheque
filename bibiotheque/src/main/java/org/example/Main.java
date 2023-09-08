package org.example;
import org.example.Controller.AuthentificationController;
import org.example.Controller.EmpruntController;
import org.example.Model.Status;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void main(String[] args) {
        AuthentificationController authentificationController=new AuthentificationController();
        EmpruntController empruntController=new EmpruntController();
        Boolean auth=authentificationController.Authenification();
        if (auth) {
            empruntController.save();
            Status status=new Status("disponible");
            System.out.print(status.getLabel());
        } else {
            System.out.println("Access denied. Please authenticate first.");
        }
    }
}