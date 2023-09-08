package org.example.Controller;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
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
            Boolean res= empruntService.save(emprunt,data);
            if (res) {
                return "the emprunt is successfully saved";
            } else {
                return "the save operation of emprunt failed";
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return "the save operation of emprunt failed";
    }

}
