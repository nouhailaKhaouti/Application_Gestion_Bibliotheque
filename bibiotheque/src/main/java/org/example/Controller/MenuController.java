package org.example.Controller;
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
        String reset = "\u001B[0m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String cyan = "\u001B[36m";
        String bold = "\u001B[1m";
        String purple="\\u001B[45m";

        // ASCII art for the header
        String header = bold + purple +
                "    _                   ____      _       ____     __   __      __  __      _      _   _       _       ____  U _____ u  __  __  U _____ u _   _     _____   \n" +
                "  |\"|        ___    U | __\")uU  /\"\\  uU |  _\"\\ u  \\ \\ / /    U|' \\/ '|uU  /\"\\  u | \\ |\"|  U  /\"\\  uU /\"___|u\\| ___\"|/U|' \\/ '|u\\| ___\"|/| \\ |\"|   |_ \" _|  \n" +
                "U | | u     |_\"_|    \\|  _ \\/ \\/ _ \\/  \\| |_) |/   \\ V /     \\| |\\/| |/ \\/ _ \\/ <|  \\| |>  \\/ _ \\/ \\| |  _ / |  _|\"  \\| |\\/| |/ |  _|\" <|  \\| |>    | |    \n" +
                "\\| |/__     | |      | |_) | / ___ \\   |  _ <    U_|\"|_u     | |  | |  / ___ \\ U| |\\  |u  / ___ \\  | |_| |  | |___   | |  | |  | |___ U| |\\  |u   /| |\\   \n" +
                "  |_____|  U/| |\\u    |____/ /_/   \\_\\  |_| \\_\\     |_|       |_|  |_| /_/   \\_\\ |_| \\_|  /_/   \\_\\  \\____|  |_____|  |_|  |_|  |_____| |_| \\_|   u |_|U   \n" +
                " //  \\\\.-,_|___|_,-._|| \\\\_  \\\\    >>  //   \\\\_.-,//|(_     <<,-,,-.   \\\\    >> ||   \\\\,-.\\\\    >>  _)(|_   <<   >> <<,-,,-.   <<   >> ||   \\\\,-._// \\\\_  \n" +
                "(_\")(\"_)\\_)-' '-(_/(__) (__)(__)  (__)(__)  (__)\\_) (__)     (./  \\.) (__)  (__)(_\")  (_/(__)  (__)(__)__) (__) (__) (./  \\.) (__) (__)(_\")  (_/(__) (__) " + reset + "\n";

        System.out.println(header);
        while (!exit) {
            System.out.println("**********\nLibrary Management Menu:**********");
            System.out.println("1."+ yellow + bold +" Ajouter Un Nouveau Livre"+reset);
            System.out.println("2. "+ cyan + bold +"Modifier Une Collection"+reset);
            System.out.println("3. "+ yellow + bold +" Supprimer Un Livre"+reset);
            System.out.println("4."+ cyan + bold +" Afficher touts les collections"+reset);
            System.out.println("5."+ yellow + bold +" Afficher Un Livre à partir de son Auteur"+reset);
            System.out.println("6."+ cyan + bold +" Afficher Un Livre à partir de son Titre"+reset);
            System.out.println("7. "+ yellow + bold +"Afficher Un Livre à partir de son NumeroInventair"+reset);
            System.out.println("8. "+ cyan + bold +"Afficher Tout Les Livres Disponible"+reset);
            System.out.println("9."+ yellow + bold +" Afficher Une collection a partir d'Isbn"+reset);
            System.out.println("10."+ cyan + bold +" Emprunter Un Livre"+reset);
            System.out.println("11. "+ yellow + bold +"Afficher Les Livres Empruntés et Les Informations d'Emprunteur "+reset);
            System.out.println("12. "+ cyan + bold +" returner un livre "+reset);
            System.out.println("13. "+ yellow + bold +"Ajouter Un Nouveau Status"+reset);
            System.out.println("14. "+ cyan + bold +"Modifier Un Nouveau Status"+reset);
            System.out.println("15. "+ yellow + bold +"Supprimer Un Nouveau Status"+reset);
            System.out.println("16. "+ cyan + bold +"Ajouter Un Nouveau emprunteur"+reset);
            System.out.println("17. "+ yellow + bold +"Modifier Un  emprunteur"+reset);
            System.out.println("18. "+ cyan + bold +"Supprimer Un  emprunteur"+reset);
            System.out.println("19. "+ yellow + bold +"Afficher touts les emprunteurs"+reset);
            System.out.println("20. "+ cyan + bold +"Afficher un emprunteur selon son memberShip"+reset);
            System.out.println("21. "+ yellow + bold +"Afficher Les Statistiques"+reset);
            System.out.println("0. "+ green + bold +"Exit"+reset);

            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:livreController.save(); break;
                    case 2:collectionController.Update();break;
                    case 3:livreController.Delete(); break;
                    case 4:collectionController.findAll();break;
                    case 5:
                        System.out.print("***** L'affichage d'un livre à partir de son autheur *****\n");
                        livreController.findByAuthor();
                        break;
                    case 6:
                        System.out.print("***** L'affichage d'un livre à partir de son titre *****\n");
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
                        System.out.print("--------- Modifie d'un Status --------- \n");
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
                    case 0:
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
