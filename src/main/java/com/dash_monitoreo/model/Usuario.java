package com.dash_monitoreo.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
    }

}
