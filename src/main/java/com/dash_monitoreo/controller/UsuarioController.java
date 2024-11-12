package com.dash_monitoreo.controller;
import com.dash_monitoreo.dto.LoginDTO;
import com.dash_monitoreo.dto.UsuarioDTO;
import com.dash_monitoreo.exception.ConflictException;
import com.dash_monitoreo.exception.NotFoundException;
import com.dash_monitoreo.model.Usuario;
import com.dash_monitoreo.service.IUsuarioService;
import com.dash_monitoreo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obtenerUsuario(id);
            return ResponseEntity.ok(usuario);
        } catch (NotFoundException ex) {
            throw new NotFoundException("El usuario con id : " + id + " no existe");
        }
    }


    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.crearUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Usuario creado con éxito"));
        } catch (ConflictException ex) {
            throw new ConflictException("El usuario con email : " + usuarioDTO.getEmail() + " ya existe");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Usuario eliminado con éxito"));
        } catch (NotFoundException ex) {
            throw new NotFoundException("El usuario con id : " + id + " no existe");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@Valid @PathVariable Long id, @RequestBody UsuarioDTO usuarioEditDTO) {
        try {
            usuarioService.actualizarUsuario(id, usuarioEditDTO);
            return ResponseEntity.ok(Collections.singletonMap("message", "Usuario actualizado con éxito"));
        } catch (NotFoundException ex) {
            throw new NotFoundException("El usuario con id : " + id + " no existe");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO usuarioLoginDTO) {
        Usuario usuario = usuarioService.login(usuarioLoginDTO);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Usuario no encontrado"));
        }

        return ResponseEntity.ok(Collections.singletonMap("user", usuario));
    }
}
