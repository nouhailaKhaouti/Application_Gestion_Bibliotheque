package org.example.Service;

import org.example.Model.Livre;
import org.example.Model.Collection;
import org.example.Repository.LivreRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreService {
   CollectionService collectionService=new CollectionService();
   LivreRepository livreRepository=new LivreRepository();
    public String save(Livre livre)throws SQLException {
        if(livreRepository.livreExists(livre.getNumeroInventair())==0) {
            Collection collection=collectionService.findByIsbn(livre.getCollection().getIsbn());
            livre.setCollection(collection);
            if (livreRepository.save(livre)) {
                return "livre is successfully added";
            }
            return "adding livre failed";
        }
        return "livre with numero inventair: "+livre.getNumeroInventair()+" already exists";
    }

    public String delete(String numero)throws SQLException{
       Livre livre= livreRepository.findByNI(numero);
       if(livre!=null){
           if(livreRepository.findByCollection(livre.getCollection().getId()).size()<1){
               boolean res=collectionService.delete(livre.getCollection().getIsbn());
               if(res){
                   System.out.print("collection with the isbn: "+livre.getCollection().getIsbn()+"has been deleted");
               }else{
                   return "error in deleting the collection";
               }
           }
           if(livreRepository.delete(livre.getId())){
               return "the livre has been deleted successfully";
           }
           return "an error has accured when deleting livre";
       }
       return "there's no livre with id:"+livre.getNumeroInventair();
    }

    public List<Livre> findAll()throws  SQLException{
        List<Livre> livres=new ArrayList<>();
        livres=livreRepository.findAll();
        if(livres.size()==0){
            return null;
        }
        return livres;
    }

    public Livre findByNI(String numero)throws SQLException{
        Livre livre=livreRepository.findByNI(numero);
        if(livre!=null){
            return livre;
        }
        return null;
    }

    public String Statistiques()throws SQLException{
        Integer dispo=livreRepository.findByStatus("Disponible").size();
        Integer perdu=livreRepository.findByStatus("Perdue").size();
        Integer emprunte=livreRepository.findByStatus("Emprunte").size();
        return "the number of books available:"+dispo+" for borrowed books  :"+emprunte+" and lastly for lost books:"+perdu;
    }

}
