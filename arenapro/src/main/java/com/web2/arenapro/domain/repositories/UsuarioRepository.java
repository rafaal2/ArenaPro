package com.web2.arenapro.domain.repositories;

import com.web2.arenapro.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
