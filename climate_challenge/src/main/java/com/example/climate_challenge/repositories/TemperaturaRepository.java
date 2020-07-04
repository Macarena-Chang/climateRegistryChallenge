package com.example.climate_challenge.repositories;

import java.util.List;
import java.util.Optional;

import com.example.climate_challenge.entities.Temperatura;
import com.example.climate_challenge.models.responses.Tempview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturaRepository  extends JpaRepository<Temperatura, Integer>  {


    List<Temperatura> findByAnio(int anio);

    @Query("Select t FROM Temperatura t WHERE t.codigoPais.codigoPais = :codigoPais")
    List<Temperatura> findAllByCodigoPais(@Param("codigoPais") int codigoPais);


  
}