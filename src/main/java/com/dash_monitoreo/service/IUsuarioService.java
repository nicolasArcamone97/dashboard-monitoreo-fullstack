package com.dash_monitoreo.service;


import com.dash_monitoreo.dto.LoginDTO;
import com.dash_monitoreo.dto.PlantaDTO;
import com.dash_monitoreo.dto.UsuarioDTO;
import com.dash_monitoreo.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> obtenerUsuarios();

    public Usuario obtenerUsuario(Long id);

    public void crearUsuario(UsuarioDTO usuario);

    public void actualizarUsuario(Long id,UsuarioDTO usuario);

    public void eliminarUsuario(Long id);

    public Usuario findByEmail(String email);

    public Usuario login(LoginDTO loginDTO);

    public void crearPlanta(Long id, PlantaDTO plantaDTO);




}
