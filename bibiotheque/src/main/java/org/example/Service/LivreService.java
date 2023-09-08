package org.example.Service;

import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Repository.LivreRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreService {
   CollectionService collectionService=new CollectionService();
   LivreRepository livreRepository=new LivreRepository();
    public String save(Livre livre)throws SQLException {
        Collection collection=livre.getCollection();
        if(collectionService.findByIsbn(collection.getIsbn())==null){
            collectionService.save(collection);
        }
        if(livreRepository.livreExists(livre.getNumeroInventair())==0) {
            if (livreRepository.save(livre)) {
                return "livre is successfully added";
            }
            return "adding livre failed";
        }
        return "livre with numero inventair: "+livre.getNumeroInventair()+" already exists";
    }

    public String delete(Long id)throws SQLException{
       Livre livre= livreRepository.findById(id);
       if(livre!=null){
           if(livreRepository.findByCollection(livre.getCollection().getId()).size()<1){
               boolean res=collectionService.delete(livre.getCollection().getIsbn());
               if(res){
                   System.out.print("collection with the isbn: "+livre.getCollection().getIsbn()+"has been deleted");
               }else{
                   return "error in deleting the collection";
               }
           }
           if(livreRepository.delete(id)){
               return "the livre has been deleted successfully";
           }
           return "an error has accured when deleting livre";
       }
       return "there's no livre with id:"+livre.getId();
    }

    public List<Livre> findAll()throws  SQLException{
        List<Livre> livres=new ArrayList<>();
        livres=livreRepository.findAll();
        if(livres.size()==0){
            return null;
        }
        return livres;
    }


}
