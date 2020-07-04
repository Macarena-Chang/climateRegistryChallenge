package com.example.climate_challenge.services;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.*;

import com.example.climate_challenge.entities.*;
import com.example.climate_challenge.models.responses.Tempview;
import com.example.climate_challenge.models.responses.TempviewMax;
import com.example.climate_challenge.repositories.PaisRepository;
import com.example.climate_challenge.repositories.TemperaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepo;

    public boolean crearTemperatura(Temperatura temperatura) {

        List<Temperatura> temperaturasExistentes = new ArrayList<>();
        temperaturasExistentes = temperaturaRepo.findAll();

        // JAVA VERSION: Check if a temperature for this country and year already exists
        // in database.
        // This could also be done by adding ALTER TABLE temperatura ADD CONSTRAINT
        // uniqueCombCountryYear UNIQUE (codigo_pais, anio)
        for (int i = 0; i < temperaturasExistentes.size(); i++) {

            Pais codigoP = temperaturasExistentes.get(i).getCodigoPais();
            int a = temperaturasExistentes.get(i).getAnio();

            if (codigoP == temperatura.getCodigoPais() && a == temperatura.getAnio()) {

                return false;
            }
        }

        temperaturaRepo.save(temperatura);
        return true;
    }

    public void borrarTemperatura(Temperatura temperatura) {

        this.actualizaTemp(temperatura, 0);

    }

    public void actualizaTemp(Temperatura temperatura, int anio) {

        temperatura.setAnio(anio);

        temperaturaRepo.save(temperatura);
    }

    public Temperatura traerTemperaturaPorId(int registroId) {

        Optional<Temperatura> t = temperaturaRepo.findById(registroId);

        if (t.isPresent()) {

            return t.get();
        }
        return null;
    }

    public List<Tempview> getTemperaturasPorAnio(int anio) {

        List<Temperatura> temperaturas = new ArrayList();
        temperaturas = temperaturaRepo.findByAnio(anio);

        List<Tempview> tempviews = new ArrayList<>();

        for (int i = 0; i < temperaturas.size(); i++) {
            Tempview t = new Tempview();
            t.nombre = temperaturas.get(i).getCodigoPais().getNombre();
            t.grados = temperaturas.get(i).getGrados();

            
            tempviews.add(t);

        }

        return tempviews;
    }

    public TempviewMax buscarMaxPorPais(int codigoPais) {

        TempviewMax tMax = new TempviewMax();

        tMax.temperaturaMaxima = 0.00;

        List<Temperatura> temperaturasList = new ArrayList<>();
        temperaturasList = temperaturaRepo.findAllByCodigoPais(codigoPais);

        for (int i = 0; i < temperaturasList.size(); i++) {

            if (temperaturasList.get(i).getGrados() > tMax.temperaturaMaxima) {

                tMax.anio = temperaturasList.get(i).getAnio();
                tMax.nombre = temperaturasList.get(i).getCodigoPais().getNombre();
                tMax.temperaturaMaxima = temperaturasList.get(i).getGrados();
            }
        }

        return tMax;

    }

}