package org.example.Service;

import org.example.Model.Emprunt;
import org.example.Model.Emprunteur;
import org.example.Model.Livre;
import org.example.Repository.EmpruntRepository;
import org.example.Repository.EmprunteurRepository;
import org.example.Repository.LivreRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpruntService {
     EmpruntRepository empruntRepository=new EmpruntRepository();
    LivreRepository livreRepository=new LivreRepository();
    EmprunteurRepository emprunteurRepository=new EmprunteurRepository();

    public String save(Emprunt emprunt, List<String> NIs)throws SQLException {
        List<Long> ids=new ArrayList<>();
       for (String NI : NIs){
           Livre livre=livreRepository.findByNI(NI);
          if(livre==null || livre.getStatus().getLabel()!="Disponible"){
              return "the book you want to add with the emprunt dosen't exist please choose one that exists";
          }
          ids.add(livre.getId());
       }
       Emprunteur emprunteur=emprunteurRepository.findByMembreShip(emprunt.getEmprunteur().getMembreShip());
       if(emprunteur!=null) {
           emprunt.setEmprunteur(emprunteur);
           if (empruntRepository.findByEmprunteur(emprunt) == 0) {
               Boolean res = empruntRepository.saveEmprunt(emprunt, ids);
               if (res) {
                   return "the emprunt is successfully saved";
               } else {
                   return "adding emprunt failed";
               }
           }
           return "this borrower didn't return the previous borrowed books ";
       }
       return " the borrower you choosed doesn't exist";
    }

    public String returne(String numeroInventair)throws  SQLException{
        Livre livre=livreRepository.findByNI(numeroInventair);
        if(livre!=null){
             Emprunt emprunt=empruntRepository.findEmpruntLivre(livre);
             if(emprunt!=null){
                 empruntRepository.delete(livre.getId());
                 if(empruntRepository.checkCount(emprunt.getId())==0){
                     empruntRepository.update(emprunt.getId());
                     return "the emprunt has been updated successfully";
                 }
                 return "book with numeroInventair :"+numeroInventair+"has been returned successfully";
             }
             return "emprunt of this book doesn't exist";
        }
        return "this book doesn't exist";
    }

    public List<Emprunt> findAll()throws  SQLException{
        List<Emprunt> emprunts=empruntRepository.findByAll();
        if(emprunts.size()==0){
            return null;
        }
        return emprunts;
    }

    public Emprunt findById(Long id)throws SQLException{
        Emprunt emprunt=empruntRepository.findById(id);
        if(emprunt!=null){
            return emprunt;
        }
        return null;
    }
}
