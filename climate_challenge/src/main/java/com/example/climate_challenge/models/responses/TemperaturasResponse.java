package com.example.climate_challenge.models.responses;

import java.util.*;

import com.example.climate_challenge.entities.*;

public class TemperaturasResponse {

 

    private int codigoPais; 
    private List temperaturas = new ArrayList<>(); 

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public List getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List temperaturas) {
        this.temperaturas = temperaturas;
    }



    

    
}