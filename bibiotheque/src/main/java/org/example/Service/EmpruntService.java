package org.example.Service;

import org.example.Model.Emprunt;
import org.example.Repository.EmpruntRepository;
import org.example.Repository.LivreRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpruntService {
     EmpruntRepository empruntRepository=new EmpruntRepository();
    LivreRepository livreRepository=new LivreRepository();

    public String save(Emprunt emprunt, List<Long> ids)throws SQLException {
       for (Long id : ids){
          if(livreRepository.findById(id)==null){
              return "the book you want to add with the emprunt dosen't exist please choose one that exists";
          }
       }
        Boolean res=empruntRepository.saveEmprunt(emprunt,ids);

        if(res){
            return "the emprunt is successfully saved";
        }else{
            return "adding emprunt failed";
        }

    }

    public String returne(Long id)throws  SQLException{
        if(empruntRepository.update(true,id)){
            return "the book has been returned successfully";
        }
        return "an error accured while changing the returne";
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
