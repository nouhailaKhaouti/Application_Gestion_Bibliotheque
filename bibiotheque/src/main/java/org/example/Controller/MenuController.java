package org.example.Controller;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MenuController {
    LivreController livreController = new LivreController();
    StatusController statusController = new StatusController();
    EmpruntController empruntController = new EmpruntController();
    CollectionController collectionController = new CollectionController();
    EmprunteurController emprunteurController = new EmprunteurController();

    //returne logique
    public void displayMenu() throws SQLException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            livreController.change_status_perdue();
            System.out.println("Function executed every 24 hours.");
        };

        long initialDelay = 0;
        long period = 24 * 60 * 60;

        scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("**********\nLibrary Management Menu:**********");
            System.out.println("1. Ajouter Un Nouveau Livre");
            System.out.println("3. Modifier Une Collection");
            System.out.println("2. Supprimer Un Livre");
            System.out.println("4. Afficher touts les collections");
            System.out.println("5. Afficher Un Livre à partir de son Auteur");
            System.out.println("6. Afficher Un Livre à partir de son Titre");
            System.out.println("7. Afficher Un Livre à partir de son NumeroInventair");
            System.out.println("8. Afficher Tout Les Livres Disponible");
            System.out.println("9. Afficher Une collection a partir d'Isbn");
            System.out.println("10. Emprunter Un Livre");
            System.out.println("11. Afficher Les Livres Empruntés et Les Informations d'Emprunteur ");
            System.out.println("12.  returner un livre ");
            System.out.println("13. Ajouter Un Nouveau Status");
            System.out.println("14. Modifier Un Nouveau Status");
            System.out.println("15. Supprimer Un Nouveau Status");
            System.out.println("16. Ajouter Un Nouveau emprunteur");
            System.out.println("17. Modifier Un  emprunteur");
            System.out.println("18. Supprimer Un  emprunteur");
            System.out.println("19. Afficher touts les emprunteurs");
            System.out.println("20. Afficher un emprunteur selon son memberShip");
            System.out.println("21. Afficher Les Statistiques");
            System.out.println("22. Exit");

            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:livreController.save(); break;
                    case 2:collectionController.Update();break;
                    case 3:livreController.Delete(); break;
                    case 4:collectionController.findAll();break;
                    case 5:
                        System.out.print("***** L'affichage d'un livre à partir de son autheur*****\n");
                        livreController.findByAuthor();
                        break;
                    case 6:
                        System.out.print("***** L'affichage d'un livre à partir de son titre*****\n");
                        livreController.findByTitre();
                        break;
                    case 7:
                        System.out.print("***** L'affichage d'un livre à partir de son numero inventair *****\n");
                        livreController.findByNI();
                        break;
                    case 8:
                        System.out.println("------- Affichage les livres disponibles --------\n");
                        livreController.findDisponible();
                        break;
                    case 9:
                        System.out.println("------- Affichage une collection à partir de ISBN --------\n");
                        collectionController.findByIsbn();
                        break;
                    case 10:
                        System.out.print("------------ Emprunter Un Livre ------------\n ");
                        empruntController.save();
                        break;
                    case 11:
                        System.out.print("---------- Affichage Des Livres Empruntés avec Les informations des Emprunteurs ----------\n ");
                        empruntController.findAll();
                        break;
                    case 12:
                        System.out.print("---------- Returner un livre ----------\n ");
                        empruntController.returne();
                        break;
                    case 13:
                        System.out.print("--------- Ajout d'un nouveau Status --------- \n");
                        statusController.save();
                        break;
                    case 14:
                        System.out.print("--------- Modifie d'un  Status --------- \n");
                        statusController.update();
                        break;
                    case 15:
                        System.out.print("--------- Suppression d'un  Status --------- \n");
                        statusController.delete();
                        break;
                    case 16:
                        System.out.print("--------- Ajout d'un nouveau Emprunteur --------- \n");
                        emprunteurController.save();
                        break;
                    case 17:
                        System.out.print("--------- Modifier d'un  Emprunteur --------- \n");
                        emprunteurController.update();
                        break;
                    case 18:
                        System.out.print("--------- Suppression d'un  Emprunteur --------- \n");
                        emprunteurController.delete();
                        break;
                    case 19:
                        System.out.print("--------- Affichage de touts les Emprunteurs --------- \n");
                        emprunteurController.findAll();
                        break;
                    case 20:
                        System.out.print("--------- Affichage d'un Emprunteur selon son MemberShip --------- \n");
                        emprunteurController.findByMemberShip();
                        break;
                    case 21:
                        System.out.print("-------- L'affichage des statistiques -----------\n");
                        livreController.statistique();
                        break;
                    case 22:
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
