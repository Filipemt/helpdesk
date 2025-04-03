package br.com.filipecode.DeskhelpApi.repositories;

import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
