package org.example.Controller;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Repository.EmpruntRepository;
import org.example.Service.EmpruntService;

public class EmpruntController {

    EmpruntRepository empruntRepository=new EmpruntRepository();
    EmpruntService empruntService=new EmpruntService(empruntRepository);

    public String save(int livreId){
        Emprunteur emprunteur=new Emprunteur();
        emprunteur.setId(1L);
        Emprunt emprunt = new Emprunt();
        emprunt.setStartDate(java.sql.Date.valueOf("2023-09-10"));
        emprunt.setEndDate(java.sql.Date.valueOf("2023-09-20"));
        emprunt.setReturne(false);
        emprunt.setEmprunteur(emprunteur);
        Emprunt empruntN=empruntService.save(emprunt,livreId);
       if(empruntN!=null){
           return empruntN.toString();
       }else{
           return "the save operation of emprunt failed";
       }
    }
}
