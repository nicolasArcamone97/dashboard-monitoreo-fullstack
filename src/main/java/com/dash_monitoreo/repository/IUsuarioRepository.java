package com.dash_monitoreo.repository;

import com.dash_monitoreo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByEmail(String email);

}
