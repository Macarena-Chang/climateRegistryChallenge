package com.example.climate_challenge.entities;

import javax.persistence.*;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;


@Entity
@Table(name="temperatura")
public class Temperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
   @ManyToOne
   @JoinColumn(name="codigo_pais" , referencedColumnName = "codigo_pais")
    private Pais codigoPais; 

    private int anio; 

    private Double grados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public Pais getCodigoPais() {
        return codigoPais;
    }


    //añadir a la lista de temperaturas en entidad Pais.
    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
        this.codigoPais.getTemperaturas().add(this);
        
    }


    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Double getGrados() {
        return grados;
    }

    public void setGrados(Double grados) {
        this.grados = grados;
    }

    public Temperatura(int codigoPais, int anio, Double grados) {
        
        this.anio = anio;
        this.grados = grados;
    }

    public Temperatura() {
    }

    
    
    
}