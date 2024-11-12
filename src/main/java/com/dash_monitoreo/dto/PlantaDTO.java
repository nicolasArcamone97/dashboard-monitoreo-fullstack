package com.dash_monitoreo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantaDTO {

    @NotBlank(message = "El nombre de la planta no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El país de la planta no puede estar vacío")
    private String pais;

    private String bandera;

    @PositiveOrZero
    private int cantLecturas;

    @PositiveOrZero
    private int cantSensores;

    @PositiveOrZero
    private int cantAlertasMedias;

    @PositiveOrZero
    private int cantAlertasAltas;

    public PlantaDTO() {
    }

    public PlantaDTO(String nombre,String pais ,String bandera, int cantLecturas, int cantSensores, int cantAlertasMedias, int cantAlertasAltas) {
        this.nombre = nombre;
        this.pais = pais;
        this.bandera = bandera;
        this.cantLecturas = cantLecturas;
        this.cantSensores = cantSensores;
        this.cantAlertasMedias = cantAlertasMedias;
        this.cantAlertasAltas = cantAlertasAltas;
    }


}
