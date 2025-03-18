package com.arrendadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arrendadora.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
