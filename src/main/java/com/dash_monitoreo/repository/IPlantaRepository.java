package com.dash_monitoreo.repository;

import com.dash_monitoreo.model.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantaRepository  extends JpaRepository<Planta,Long> {
    public Planta findByNombre(String nombre);
}
