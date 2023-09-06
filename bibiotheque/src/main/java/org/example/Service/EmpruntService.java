package org.example.Service;

import org.example.Model.Emprunt;
import org.example.Repository.EmpruntRepository;
import org.example.Repository.UserRepository;

public class EmpruntService {
    private EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }
    public Emprunt save(Emprunt emprunt,int livreId){
        Emprunt empruntN=empruntRepository.saveEmprunt(emprunt,livreId);

        if(empruntN!=null){
            return empruntN;
        }else{
            return null;
        }

    }
}
