package com.example.climate_challenge.controllers;

import java.util.*;

import com.example.climate_challenge.entities.Pais;
import com.example.climate_challenge.entities.Temperatura;
import com.example.climate_challenge.models.requests.TemperaturaRequest;
import com.example.climate_challenge.models.responses.GenericResponse;
import com.example.climate_challenge.models.responses.Tempview;
import com.example.climate_challenge.services.PaisService;
import com.example.climate_challenge.services.TemperaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperaturaController {

    
    @Autowired
    TemperaturaService temperaturaService;

    @Autowired
    PaisService paisService; 

    @PostMapping("/temperaturas")
    public ResponseEntity<?> postTemperatura(@RequestBody TemperaturaRequest tempRequest) {
        Temperatura temperatura = new Temperatura(); 
        temperatura.setCodigoPais(paisService.buscarPaisById(tempRequest.codigoPais));
        temperatura.setGrados(tempRequest.grados);
        temperatura.setAnio(tempRequest.anio);
        GenericResponse response = new GenericResponse();

        boolean result =   temperaturaService.crearTemperatura(temperatura);  
       

        if (result) {  
            response.id = temperatura.getId();        
            response.isOk = true;
            response.message = "ID: "+ response.id + " El registro se realizó exitosamente.";
            return ResponseEntity.ok(response);
        } else {

            response.isOk = false;
            response.message = "Error";

            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable int id) {

        Temperatura temperatura = temperaturaService.traerTemperaturaPorId(id);

        if (temperatura != null) {

            temperaturaService.borrarTemperatura(temperatura);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = temperatura.getId();
            resp.message = "Fue eliminado con exito";

            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @GetMapping("/temperaturas/anios/{anio}")
    public ResponseEntity<?> getTempPorAnio(@PathVariable int anio){

        List<Tempview> temp = new ArrayList<>(); 
        temp = temperaturaService.getTemperaturasPorAnio(anio);

        if (temp.isEmpty()) {            

            GenericResponse resp = new GenericResponse();
            resp.isOk = false;            
            resp.message = "No hay resultados";

            return ResponseEntity.ok(resp);
        }

        return ResponseEntity.ok(temp); 

    }

    








    
}