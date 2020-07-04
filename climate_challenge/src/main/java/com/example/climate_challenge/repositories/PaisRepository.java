package com.example.climate_challenge.repositories;

import java.util.Optional;

import com.example.climate_challenge.entities.Pais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    
    Pais findByCodigoPais(int codigoPais);
}