package com.dash_monitoreo.controller;

import com.dash_monitoreo.dto.PlantaDTO;
import com.dash_monitoreo.exception.BadRequestException;
import com.dash_monitoreo.exception.NotFoundException;
import com.dash_monitoreo.model.Planta;
import com.dash_monitoreo.service.IPlantaService;
import com.dash_monitoreo.service.PlantaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/planta")
public class PlantaController {

    @Autowired
    private IPlantaService plantaService;


    @GetMapping()
    public ResponseEntity<?> getPlantas(){
        return new ResponseEntity<>(plantaService.obtenerPlantas(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanta(@PathVariable Long id){
        try {
            Planta planta = plantaService.obtenerPlanta(id);
            return ResponseEntity.ok(Collections.singletonMap("planta", planta));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Planta no encontrada con ID: " + id);
        }
    }

    @PostMapping()
    public ResponseEntity<?> crearPlanta(@Valid @RequestBody PlantaDTO planta){
        try {
            plantaService.crearPlanta(planta);
            return ResponseEntity.ok(Collections.singletonMap("message", "Planta creada con éxito"));
        } catch (BadRequestException ex) {
            throw new BadRequestException("Error al crear la planta");
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanta(@PathVariable Long id){
        try {
            plantaService.eliminarPlanta(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Planta eliminada con éxito"));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Planta no encontrada con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlanta(@Valid @PathVariable Long id, @RequestBody PlantaDTO planta){
        try {
            plantaService.actualizarPlanta(id, planta);
            return ResponseEntity.ok(Collections.singletonMap("message", "Planta actualizada con éxito"));
        } catch (NotFoundException ex) {
            throw new NotFoundException("Planta no encontrada con ID: " + id);
        }
    }
}