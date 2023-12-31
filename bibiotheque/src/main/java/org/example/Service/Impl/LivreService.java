package org.example.Service.Impl;

import org.example.Model.Livre;
import org.example.Model.Collection;
import org.example.Repository.LivreRepository;
import org.example.Service.Impl.CollectionService;

import java.sql.SQLException;
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
           if(livre.getCollection().getTotale()==1){
               boolean res=collectionService.delete(livre.getCollection().getIsbn());
               if(res){
                   return "collection with the isbn: "+livre.getCollection().getIsbn()+"has been deleted along with the book : "+livre.getNumeroInventair();
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
        List<Livre> livres=livreRepository.findAll();
        if(livres.size()==0){
            return null;
        }
        return livres;
    }
    public List<Livre> findDisponible()throws  SQLException{
        List<Livre> livres=livreRepository.findByStatus("Disponible");
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

    public List<Livre> findByAuthor(String author)throws SQLException{
        List<Livre> livres=livreRepository.findByAttribute(author,"auteur");
        if(livres!=null){
            return livres;
        }
        return null;
    }

    public List<Livre> findByTitre(String title)throws SQLException{
        List<Livre> livres=livreRepository.findByAttribute(title,"title");
        if(livres!=null){
            return livres;
        }
        return null;
    }

    public Integer Statistiques(String label)throws SQLException{
        Integer statistique=livreRepository.findByStatus(label).size();
        return statistique;
    }

    public String change_status_perdue(){
        if(livreRepository.change_status_perdue()){
            return "the status of lost books has changed successfully";
        }
        return "an error occurred while tring to change books status to lost ";
    }

}
