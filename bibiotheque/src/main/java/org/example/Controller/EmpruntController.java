package org.example.Controller;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Service.Impl.EmpruntService;
import org.example.Service.Impl.EmprunteurService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//date end automation
public class EmpruntController {
    EmpruntService empruntService=new EmpruntService();
    EmprunteurService emprunteurService=new EmprunteurService();
    EmprunteurController emprunteurController=new EmprunteurController();
    Scanner scanner = new Scanner(System.in);
    public void save() throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> data = new ArrayList<>();
        int exit = 1;
        String member = null;
        Emprunteur emprunteur = null;

        while (exit!=0 && exit!=2) {
            System.out.print("Enter Emprunteur memberShip : ");
            member = scanner.next();
            emprunteur = emprunteurService.findByMemberShip(member);

            if (emprunteur == null) {
                System.out.println("This emprunteur doesn't exist");
                System.out.println("Choose an option:");
                System.out.println("1. Re-enter the membership of the borrower");
                System.out.println("2. Create a new borrower");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        emprunteurController.save();
                        emprunteur = emprunteurService.findByMemberShip(member);
                        exit=2;
                        break;
                    case 3:
                        System.out.println("Exiting.");
                        exit = 0; // Set the exit flag to true
                        break;
                    default:
                        System.out.println("Invalid choice. Exiting.");
                        exit = 0; // Set the exit flag to true
                        break;
                }
            } else {
                exit = 2;
            }
        }
        if (exit==0) {
            return;
        }
        System.out.print("\n Enter End date (YYYY-MM-DD): ");
        String EndDate = scanner.next();
        emprunteur.setMembreShip(member);

        System.out.print("Enter the number of livres empruntés by the emprunteur: ");
        Integer n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the numero d'inventair of " + (i + 1) + " livre: ");
            String id = scanner.next();
            data.add(id);
        }
        try {
            Emprunt emprunt = new Emprunt(new Date(),dateFormat.parse(EndDate),false,emprunteur);
            System.out.print(empruntService.save(emprunt, data) + "\n");
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
            System.out.print("The save operation of emprunt failed.\n");
        }
    }


    public void returne()throws SQLException{
        System.out.print("Enter Livre numero Inventair : ");
        String numero = scanner.next();
        System.out.print( empruntService.returne(numero));
    }

    public void findAll() throws SQLException {
        List<Emprunt> emprunts = empruntService.findAll();

        for (Emprunt emprunt : emprunts) {
            if (!emprunt.getReturne()) {
                System.out.println("+------------------+-------------------------+---------------------+-------------------------+-----------------------+");
                System.out.printf("| %-15s | %-15s | %-30s | %-20s | %-30s |%n","Date","State","name","memberShip","Email");
                System.out.println("+------------------+-------------------------+---------------------+-------------------------+-----------------------+");
                System.out.printf(
                        "| %-15s - %-15s | %-30s | %-20s | %-30s |%n",
                        emprunt.getStartDate(),
                        emprunt.getEndDate(),
                        emprunt.getReturne(),
                        emprunt.getEmprunteur().getFullName(),
                        emprunt.getEmprunteur().getMembreShip(),
                        emprunt.getEmprunteur().getEmail()
                );

                List<Livre> livres = emprunt.getLivreList();
                System.out.println("+------------------------------------------------------------------------------------+");
                System.out.println("| Livres Borrowed:                                                                   |");
                System.out.println("+------------------+----------------+----------------+----------------+---------------+");
                System.out.printf("| %-15s | %-20s | %-30s | %-20s |%n","NumeroInventair","Isbn","Title","Auteur");
                System.out.println("+------------------+----------------+----------------+----------------+---------------+");

                for (Livre livre : livres) {
                    Collection collection = livre.getCollection();
                    System.out.printf(
                            "| %-15s | %-20s | %-30s | %-20s |%n",
                            livre.getNumeroInventair(),
                            collection.getIsbn(),
                            collection.getTitle(),
                            collection.getAuteur()
                    );
                    System.out.println("+------------------+----------------+----------------+----------------+---------------+");
                }
                System.out.println("+--------------------------------------------------------------------------------------+ \n\n\n");
            }
        }
    }

}
