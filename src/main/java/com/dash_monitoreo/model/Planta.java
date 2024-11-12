package com.dash_monitoreo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String nombre;

    @Column(nullable = false)
    private String pais;

    private String bandera;

    private int cantLecturas;

    private int cantSensores;

    private int cantAlertasMedias;

    private int cantAlertasAltas;


    public Planta() {
    }

    public Planta(String nombre, String pais, String bandera , int cantLecturas, int cantSensores, int cantAlertasMedias, int cantAlertasAltas) {
        this.pais = pais;
        this.nombre = nombre;
        this.bandera = bandera;
        this.cantLecturas = cantLecturas;
        this.cantSensores = cantSensores;
        this.cantAlertasMedias = cantAlertasMedias;
        this.cantAlertasAltas = cantAlertasAltas;
    }

}