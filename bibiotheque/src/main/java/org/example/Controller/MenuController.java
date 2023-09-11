package org.example.Controller;

import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Model.Status;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuController {
    LivreController livreController = new LivreController();
    StatusController statusController = new StatusController();
    EmpruntController empruntController = new EmpruntController();
    CollectionController collectionController = new CollectionController();
    EmprunteurController emprunteurController = new EmprunteurController();

    public void displayMenu() throws SQLException {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("**********\nLibrary Management Menu:**********");
            System.out.println("1. Ajouter Un Nouveau Livre");
            System.out.println("2. Supprimer Un Livre");
            System.out.println("3. Modifier Une Collection");
            System.out.println("4. Afficher touts les collections");
            System.out.println("5. Afficher Un Livre à partir de son Auteur");
            System.out.println("6. Afficher Tout Les Livres ");
            System.out.println("7. Ajouter Une Nouvelle Collection");
            System.out.println("8. Afficher Une collection a partir d'Isbn");
            System.out.println("9. Emprunter Un Livre");
            System.out.println("10. Afficher Les Livres Empruntés et Les Informations d'Emprunteur ");
            System.out.println("11. Ajouter Un Nouveau Status");
            System.out.println("12. Ajouter Un Nouveau emprunteur");
            System.out.println("13. Modifier Un  emprunteur");
            System.out.println("14. Supprimer Un  emprunteur");
            System.out.println("15. Afficher touts les emprunteurs");
            System.out.println("16. Afficher un emprunteur selon son memberShip");
            System.out.println("17. Afficher Les Statistiques");
            System.out.println("18. Exit");

            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        livreController.save();
                        break;
                    case 2:
                        livreController.Delete();
                        break;
                    case 3:
                        collectionController.Update();
                        break;
                    case 4:
                        collectionController.findAll();
                        break;
                    case 5:
                        System.out.print("***** L'affichage d'un livre à partir de son numero inventair*****\n");
                        livreController.findByNI();
                        break;
                    case 6:
                        System.out.print("***** L'affichage e tout les livres et chacun avec leur statuts*****\n");
                        livreController.findAll();

                        break;
                    case 7:
                        System.out.print("***** L'ajout d'une nouvelle collection *****\n");
                        break;
                    case 8:
                        System.out.println("------- Affichage du Collection à partir d'Isbn --------\n");
                        collectionController.findByIsbn();
                        break;
                    case 9:
                        System.out.print("------------ Emprunter Un Livre ------------\n ");
                        empruntController.save();
                        break;
                    case 10:
                        System.out.print("---------- Affichage Des Livres Empruntés avec Les informations des Emprunteurs ----------\n ");
                        empruntController.findAll();
                        break;
                    case 11:
                        System.out.print("--------- Ajout d'un nouveau Status --------- \n");
                        statusController.save();
                        break;
                    case 12:
                        System.out.print("--------- Ajout d'un nouveau Emprunteur --------- \n");
                        emprunteurController.save();
                        break;
                    case 13:
                        System.out.print("--------- Modifier d'un  Emprunteur --------- \n");
                        emprunteurController.update();
                        break;
                    case 14:
                        System.out.print("--------- Supprimer d'un  Emprunteur --------- \n");
                        emprunteurController.delete();
                        break;
                    case 15:
                        System.out.print("--------- Affichage de touts les Emprunteurs --------- \n");
                        emprunteurController.findAll();
                        break;
                    case 16:
                        System.out.print("--------- Affichage d'un Emprunteur selon son MemberShip --------- \n");
                        emprunteurController.findByMemberShip();
                        break;
                    case 17:
                        System.out.print("-------- L'affichage des statistiques -----------\n");
                        livreController.statistique();
                        break;
                    case 18:
                        System.out.print("-------- returner les livres -----------\n");
                        empruntController.returne();
                        break;
                    case 19:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } else {
                scanner.next();
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }
    }
}
