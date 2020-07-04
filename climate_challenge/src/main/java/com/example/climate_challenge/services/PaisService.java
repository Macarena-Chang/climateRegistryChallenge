package com.example.climate_challenge.services;

import java.util.List;
import java.util.Optional;

import com.example.climate_challenge.entities.Pais;
import com.example.climate_challenge.models.requests.PaisRequest;
import com.example.climate_challenge.repositories.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepo; 

    public List<Pais> getPaises(){
       return paisRepo.findAll();
    }


    public void savePais(Pais pais){
        paisRepo.save(pais);
    }

    public Pais buscarPaisById(int id){
        Optional<Pais> paisOptional = paisRepo.findById(id);
        if(paisOptional.isPresent()){
            return paisOptional.get();
        }
        return null; 
    }


    public boolean actualizarPais(Pais paisOriginal, PaisRequest paisConInfoNueva){
        paisOriginal.setNombre(paisConInfoNueva.getNombre());
        
        savePais(paisOriginal);
        
        return true; 
    }
    
}