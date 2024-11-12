package com.dash_monitoreo.service;


import com.dash_monitoreo.dto.PlantaDTO;
import com.dash_monitoreo.model.Planta;

import java.util.List;

public interface IPlantaService {


    public void crearPlanta(PlantaDTO planta);

    public Planta obtenerPlanta(Long id);

    public void actualizarPlanta(Long id,PlantaDTO planta);

    public void eliminarPlanta(Long id);

    public List<Planta> obtenerPlantas();

    public Planta findByName(String nombre);

}
