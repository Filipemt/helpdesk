package br.com.filipecode.DeskhelpApi.usuario.repository;

import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByNome(String nome);
    List<Usuario> findByDepartamento(String departamento);
    List<Usuario> findByNomeAndDepartamento(String nome, String departamento);

    boolean existsByEmail(String email);
}
