package com.dash_monitoreo.service;

import com.dash_monitoreo.dto.PlantaDTO;
import com.dash_monitoreo.exception.ConflictException;
import com.dash_monitoreo.exception.NotFoundException;
import com.dash_monitoreo.model.Planta;
import com.dash_monitoreo.repository.IPlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaService implements IPlantaService {

    @Autowired
    IPlantaRepository plantaRepository;


    @Override
    public void crearPlanta(PlantaDTO plantaDTO) {
        Planta plantaExistente = plantaRepository.findByNombre(plantaDTO.getNombre());

        if (plantaExistente != null) {
            throw new ConflictException("Planta ya existe con nombre: " + plantaDTO.getNombre());
        }

        Planta nuevaPlanta = new Planta();
        nuevaPlanta.setNombre(plantaDTO.getNombre());
        nuevaPlanta.setPais(plantaDTO.getPais());
        nuevaPlanta.setBandera(plantaDTO.getBandera());
        nuevaPlanta.setCantLecturas(plantaDTO.getCantLecturas());
        nuevaPlanta.setCantSensores(plantaDTO.getCantSensores());
        nuevaPlanta.setCantAlertasMedias(plantaDTO.getCantAlertasMedias());
        nuevaPlanta.setCantAlertasAltas(plantaDTO.getCantAlertasAltas());

        plantaRepository.save(nuevaPlanta);
    }


    @Override
    public Planta obtenerPlanta(Long id) {
        Planta plantaBuscada = plantaRepository.findById(id).orElse(null);

        if (plantaBuscada == null) {
            throw new NotFoundException("Planta no encontrada con id: " + id);
        }

        return plantaBuscada;
    }


    @Override
    public void actualizarPlanta(Long id, PlantaDTO plantaDTO) {
        Planta plantaBuscada = plantaRepository.findById(id).orElse(null);

        if (plantaBuscada == null) {
            throw new NotFoundException("Planta no encontrada con id: " + id);
        }

        plantaBuscada.setNombre(plantaDTO.getNombre());
        plantaBuscada.setCantLecturas(plantaDTO.getCantLecturas());
        plantaBuscada.setCantSensores(plantaDTO.getCantSensores());
        plantaBuscada.setCantAlertasMedias(plantaDTO.getCantAlertasMedias());
        plantaBuscada.setCantAlertasAltas(plantaDTO.getCantAlertasAltas());

        plantaRepository.save(plantaBuscada);
    }


    @Override
    public void eliminarPlanta(Long id) {
        Planta plantaBuscada = plantaRepository.findById(id).orElse(null);

        if (plantaBuscada == null) {
            throw new NotFoundException("Planta no encontrada con id: " + id);
        }

        plantaRepository.deleteById(id);
    }


    @Override
    public List<Planta> obtenerPlantas() {
        return plantaRepository.findAll();
    }

    @Override
    public Planta findByName(String nombre) {
        Planta plantaBuscada =  plantaRepository.findByNombre(nombre);

        if (plantaBuscada == null) {
            throw new NotFoundException("Planta no encontrada con nombre: " + nombre);
        }

        return plantaBuscada;
    }

}