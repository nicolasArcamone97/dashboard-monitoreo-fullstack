package com.dash_monitoreo.service;
import com.dash_monitoreo.dto.LoginDTO;
import com.dash_monitoreo.dto.PlantaDTO;
import com.dash_monitoreo.dto.UsuarioDTO;
import com.dash_monitoreo.exception.ConflictException;
import com.dash_monitoreo.exception.NotFoundException;
import com.dash_monitoreo.model.Planta;
import com.dash_monitoreo.model.Usuario;
import com.dash_monitoreo.repository.IPlantaRepository;
import com.dash_monitoreo.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPlantaRepository plantaRepository;


    @Override
    public List<Usuario> obtenerUsuarios() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        Usuario usuarioBuscado = usuarioRepository.findById(id).orElse(null);

        if(usuarioBuscado == null){
            throw new NotFoundException("Usuario no encontrado con id: " + id);
        }
        return usuarioBuscado;
    }

    @Override
    public void crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = this.findByEmail(usuarioDTO.getEmail());

        if (usuarioExistente != null) {
            throw new ConflictException("Usuario ya existe con email: " + usuarioDTO.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());

        usuarioRepository.save(usuario);
    };

    @Override
    public void actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente == null) {
            throw new NotFoundException("Usuario no encontrado con id: " + id);
        }

        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setPassword(usuarioDTO.getPassword());
        usuarioRepository.save(usuarioExistente);
    };

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            throw new NotFoundException("Usuario no encontrado con id: " + id);
        }

        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail());

        if(usuario == null){
            throw new NotFoundException("Usuario no encontrado con email: " + loginDTO.getEmail());
        }

        if (!usuario.getPassword().equals(loginDTO.getPassword())) {
            throw new NotFoundException("La contraseña no coincide");
        }

        return usuario;
    }


    @Override
    public void crearPlanta(Long usuarioId, PlantaDTO plantaDTO) {
        // Buscar usuario por ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con ID: " + usuarioId));

        // Validar si ya existe una planta con el mismo nombre para este usuario
        boolean plantaExistente = usuario.getPlantas().stream()
                .anyMatch(planta -> planta.getNombre().equalsIgnoreCase(plantaDTO.getNombre()));

        if (plantaExistente) {
            throw new ConflictException("El usuario ya tiene una planta con el nombre: " + plantaDTO.getNombre());
        }

        // Crear la nueva planta
        Planta nuevaPlanta = new Planta();
        nuevaPlanta.setNombre(plantaDTO.getNombre());
        nuevaPlanta.setPais(plantaDTO.getPais());
        nuevaPlanta.setBandera(plantaDTO.getBandera());
        nuevaPlanta.setCantLecturas(plantaDTO.getCantLecturas());
        nuevaPlanta.setCantAlertasMedias(plantaDTO.getCantAlertasMedias());
        nuevaPlanta.setCantAlertasAltas(plantaDTO.getCantAlertasAltas());
        nuevaPlanta.setUsuario(usuario);

        // Guardar la planta en la base de datos
        plantaRepository.save(nuevaPlanta);
    }


}
