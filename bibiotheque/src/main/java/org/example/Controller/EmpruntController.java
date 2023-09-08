package org.example.Controller;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Repository.EmpruntRepository;
import org.example.Service.EmpruntService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmpruntController {
    EmpruntService empruntService=new EmpruntService();

    public String save(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Emprunteur emprunteur=new Emprunteur();
        Scanner scanner = new Scanner(System.in);
        List<Long> data = new ArrayList<>();
        System.out.print("Enter Emprunteur id : ");
        long emprunteur_id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter End date: ");
        String EndDate = scanner.nextLine();
        emprunteur.setId(emprunteur_id);
        System.out.print("Entrer le nombre des livres emprunté par l'emprunteur ");
        Integer n= scanner.nextInt();
         for(int i=0;i<n;i++){
             System.out.print("Entrer id de  "+i+"livre");
             Long id=scanner.nextLong();
             data.add(id);
         }
        try {
            Emprunt emprunt = new Emprunt();
            emprunt.setStartDate(new Date());
            emprunt.setEndDate(dateFormat.parse(EndDate));
            emprunt.setReturne(false);
            emprunt.setEmprunteur(emprunteur);
            return empruntService.save(emprunt,data);
        }catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        return "the save operation of emprunt failed";
    }

    public String returne(Long id)throws SQLException{
        return empruntService.returne(id);
    }

    public void findAll()throws SQLException{
        List<Emprunt> emprunts = empruntService.findAll();

        // Iterate through emprunts and print information
        for (Emprunt emprunt : emprunts) {
            System.out.println("Emprunt Date: " + emprunt.getStartDate());
            System.out.println("Emprunt Date: " + emprunt.getEndDate());
            System.out.println("Emprunt State: " + emprunt.getReturne());
            System.out.println("Emprunteur name: " + emprunt.getEmprunteur().getFullName());
            System.out.println("Emprunteur memberShip: " + emprunt.getEmprunteur().getMembreShip());
            List<Livre> livres = emprunt.getLivreList();
            System.out.println("{");
            for (Livre livre : livres) {
                Collection collection= livre.getCollection();
                System.out.println("   Livre NumeroInventair: " + livre.getNumeroInventair());
                System.out.println("   Livre Isbn: " + collection.getIsbn());
                System.out.println("   Livre Title: " + collection.getTitle());
                System.out.println("   Livre Auteur: " + collection.getAuteur());
                System.out.print(",");
            }
            System.out.println("}");

            System.out.println();
        }
        System.out.print(",");
    }
}
