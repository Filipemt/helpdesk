package br.com.filipecode.DeskhelpApi.usuario.repository;

import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByNome(String nome);
    List<Usuario> findByDepartamento(String departamento);
    List<Usuario> findByNomeAndDepartamento(String nome, String departamento);
    List<Usuario> findAllByRole(Role role);
    List<Usuario> findByEmail(String email);

    Optional<Usuario> findOneByEmail(String email);

    boolean existsByEmail(String email);
}
