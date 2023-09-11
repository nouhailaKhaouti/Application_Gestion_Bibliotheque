package org.example.Controller;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Service.EmpruntService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmpruntController {
    EmpruntService empruntService=new EmpruntService();
    Scanner scanner = new Scanner(System.in);
    public void save(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Emprunteur emprunteur=new Emprunteur();

        List<String> data = new ArrayList<>();
        System.out.print("Enter Emprunteur memberShip : ");
        String member = scanner.next();
        System.out.print("Enter End date: ");
        String EndDate = scanner.next();
        emprunteur.setMembreShip(member);
        System.out.print("Entrer le nombre des livres emprunté par l'emprunteur ");
        Integer n= scanner.nextInt();
         for(int i=0;i<n;i++){
             System.out.print("Entrer le numero d'inventair de "+(i+1)+" livre");
             String id=scanner.next();
             data.add(id);
         }
        try {
            Emprunt emprunt = new Emprunt();
            emprunt.setStartDate(new Date());
            emprunt.setEndDate(dateFormat.parse(EndDate));
            emprunt.setReturne(false);
            emprunt.setEmprunteur(emprunteur);
            System.out.print( empruntService.save(emprunt,data)+"\n");
        }catch (ParseException | SQLException e) {
            e.printStackTrace();
            System.out.print("the save operation of emprunt failed \n");
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
            if (emprunt.getReturne() == false) {
                System.out.println("+----------------------------------------------------+");
                System.out.println("| Emprunt Date: " + emprunt.getStartDate() + " - " + emprunt.getEndDate());
                System.out.println("| Emprunt State: " + emprunt.getReturne());
                System.out.println("| Emprunteur name: " + emprunt.getEmprunteur().getFullName());
                System.out.println("| Emprunteur memberShip: " + emprunt.getEmprunteur().getMembreShip());
                System.out.println("| Emprunteur phone number: " + emprunt.getEmprunteur().getPhone());
                System.out.println("| Emprunteur Email: " + emprunt.getEmprunteur().getEmail());

                List<Livre> livres = emprunt.getLivreList();
                System.out.println("| Livres Borrowed:");
                for (Livre livre : livres) {
                    Collection collection = livre.getCollection();
                    System.out.println("|   - Livre NumeroInventair: " + livre.getNumeroInventair());
                    System.out.println("|   - Livre Isbn: " + collection.getIsbn());
                    System.out.println("|   - Livre Title: " + collection.getTitle());
                    System.out.println("|   - Livre Auteur: " + collection.getAuteur());
                }
                System.out.println("+----------------------------------------------------+");
            }
        }
    }

}
