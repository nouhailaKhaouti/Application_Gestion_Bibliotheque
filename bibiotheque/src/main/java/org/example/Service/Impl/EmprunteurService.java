package org.example.Service.Impl;

import org.example.Model.Collection;
import org.example.Model.Emprunteur;
import org.example.Repository.EmprunteurRepository;

import java.sql.SQLException;
import java.util.List;

public class EmprunteurService {

    EmprunteurRepository emprunteurRepository=new EmprunteurRepository();

    public boolean save(Emprunteur emprunteur)throws SQLException{
        boolean res= emprunteurRepository.save(emprunteur);
        if(res){
            return true;
        }
        return false;
    }

    public String update(Emprunteur emprunteur)throws SQLException{
        boolean res= emprunteurRepository.update(emprunteur);
        if(res){
            return "this Emprunteur is successfully updated";
        }
        return "an error occurred while updating this Emprunteur try again";
    }

    public boolean delete(Long id)throws SQLException{
        boolean res= emprunteurRepository.delete(id);
        if(res){
            return true;
        }
        return false;
    }

    public List<Emprunteur> findAll()throws SQLException{
        List<Emprunteur> emprunteurs=emprunteurRepository.findAll();
        if(emprunteurs!=null){
            return emprunteurs;
        }else{
            return null;
        }
    }

    public Emprunteur findByMemberShip(String membership)throws SQLException{
        Emprunteur emprunteur=emprunteurRepository.findByMembreShip(membership);
        if(emprunteur!=null){
            return emprunteur;
        }else{
            return null;
        }
    }

    public Emprunteur findById(Long id)throws SQLException{
        Emprunteur emprunteur=emprunteurRepository.findById(id);
        if(emprunteur!=null){
            return emprunteur;
        }
        return null;
    }
}
