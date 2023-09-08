package org.example.Service;

import org.example.Model.Collection;
import org.example.Repository.CollectionRepository;
import org.example.Repository.EmpruntRepository;

import java.sql.SQLException;
import java.util.List;

public class CollectionService {
    CollectionRepository collectionRepository=new CollectionRepository();
    public Boolean save(Collection collection) throws SQLException {
        if(collectionRepository.findByIsbn(collection.getIsbn())!=null){
            boolean res= collectionRepository.save(collection);
            if(res){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public String update(Collection collection) throws SQLException {
        if(collectionRepository.collectionExists(collection.getIsbn())>0){
            boolean res= collectionRepository.update(collection);
            if(res){
                return "collection has been updated successfully";
            }else{
                return "updating collection has failed";
            }
        }else{
            return "collection with isbn:"+collection.getIsbn()+" doesn't exist";
        }

    }

    public List<Collection> findAll()throws  SQLException{
        List<Collection> collections=collectionRepository.findAll();
        if(collections!=null){
            return collections;
        }else{
            return null;
        }
    }

    public Collection findByIsbn(String isbn)throws SQLException{
        Collection collection=collectionRepository.findByIsbn(isbn);
        if(collection!=null){
            return collection;
        }else{
            return null;
        }
    }

    public Boolean delete(String isbn)throws SQLException{
        Collection collection=collectionRepository.findByIsbn(isbn);
        if(collection!=null){
            boolean res= collectionRepository.delete(isbn);
            if(res){
                return true;
            }else{
                return false;
            }
        }else{
            return null;
        }
    }

}
