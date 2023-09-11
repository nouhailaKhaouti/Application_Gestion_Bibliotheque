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

        List<Long> data = new ArrayList<>();
        System.out.print("Enter Emprunteur id : ");
        long emprunteur_id = scanner.nextLong();
        System.out.print("Enter End date: ");
        String EndDate = scanner.next();
        emprunteur.setId(emprunteur_id);
        System.out.print("Entrer le nombre des livres emprunté par l'emprunteur ");
        Integer n= scanner.nextInt();
         for(int i=0;i<n;i++){
             System.out.print("Entrer id de"+i+"livre");
             Long id=scanner.nextLong();
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

    public String returne()throws SQLException{
        System.out.print("Enter Emprunt id : ");
        long id = scanner.nextLong();
        return empruntService.returne(id);
    }

    public void findAll()throws SQLException{
        List<Emprunt> emprunts = empruntService.findAll();

        for (Emprunt emprunt : emprunts) {
            if(emprunt.getReturne()==false) {
                System.out.println("Emprunt Date: " + emprunt.getStartDate());
                System.out.println("Emprunt Date: " + emprunt.getEndDate());
                System.out.println("Emprunt State: " + emprunt.getReturne());
                System.out.println("Emprunteur name: " + emprunt.getEmprunteur().getFullName());
                System.out.println("Emprunteur memberShip: " + emprunt.getEmprunteur().getMembreShip());
                System.out.println("Emprunteur phone number: " + emprunt.getEmprunteur().getPhone());
                System.out.println("Emprunteur Email: " + emprunt.getEmprunteur().getEmail());
                List<Livre> livres = emprunt.getLivreList();
                System.out.println("{");
                for (Livre livre : livres) {
                    Collection collection = livre.getCollection();
                    System.out.println("   Livre NumeroInventair: " + livre.getNumeroInventair());
                    System.out.println("   Livre Isbn: " + collection.getIsbn());
                    System.out.println("   Livre Title: " + collection.getTitle());
                    System.out.println("   Livre Auteur: " + collection.getAuteur());
                    System.out.print(",");
                }
                System.out.println("}");
            }
            System.out.println();
        }
        System.out.print(",");
    }
}
