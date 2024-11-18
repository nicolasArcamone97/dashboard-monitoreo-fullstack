package com.dash_monitoreo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min =3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }


}
