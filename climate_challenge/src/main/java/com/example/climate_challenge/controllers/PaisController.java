package com.example.climate_challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;

import com.example.climate_challenge.entities.Pais;
import com.example.climate_challenge.entities.Temperatura;
import com.example.climate_challenge.models.requests.PaisRequest;
import com.example.climate_challenge.models.responses.TemperaturasResponse;
import com.example.climate_challenge.repositories.TemperaturaRepository;
import com.example.climate_challenge.services.PaisService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PaisController {

    @Autowired
    PaisService paisService; 
    
    @GetMapping("/paises")
    public List<Pais> traerAllPaises(){
        List<Pais> paises = new ArrayList<>();
        paises = paisService.getPaises(); 
        return paises; 
    }

    @PostMapping("/paises")
    public  ResponseEntity<?>  savePais(@RequestBody Pais pais) {
        //TODO: process POST request
        paisService.savePais(pais);
        return ResponseEntity.ok(pais);
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable int id){
        Pais pais = paisService.buscarPaisById(id);
        if(pais ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }
        return ResponseEntity.ok(pais);
    }

    @PutMapping("/paises/{id}")
    public ResponseEntity<?> actualizarNombrePais(@PathVariable int id, @RequestBody PaisRequest request){

       
        Pais paisOriginal = paisService.buscarPaisById(id);

        if(paisOriginal ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }

        paisService.actualizarPais(paisOriginal, request);

        return ResponseEntity.ok(paisOriginal);
    }


    @GetMapping("/temperaturas/paises/{codigoPais}")
    public ResponseEntity<?> findAllByCodigoPais(@PathVariable int codigoPais){
         
       
      
        TemperaturasResponse tempeResponse = new TemperaturasResponse();  
        tempeResponse = temperaturaPorPais(codigoPais);

        if(tempeResponse ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }

        return ResponseEntity.ok(tempeResponse); 
    }



    public TemperaturasResponse temperaturaPorPais(int id){
      
       Pais pais =  new Pais();

       List<Temperatura> temperaturas = new ArrayList<>();

       TemperaturasResponse tempResponse = new TemperaturasResponse(); 

       pais = paisService.buscarPaisById(id);

       tempResponse.setCodigoPais(id); 

       tempResponse.setTemperaturas(pais.getTemperaturas()); 

        return tempResponse;

    }
    
    
}