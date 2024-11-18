package com.dash_monitoreo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Planta> plantas = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
    }

}
