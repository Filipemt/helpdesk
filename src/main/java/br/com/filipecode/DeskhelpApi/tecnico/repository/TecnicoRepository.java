package br.com.filipecode.DeskhelpApi.tecnico.repository;


import br.com.filipecode.DeskhelpApi.tecnico.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TecnicoRepository extends JpaRepository<Tecnico, UUID> {
    List<Tecnico> findByEspecializacao(String especializacao);

    boolean existsByEmail(String email);
}
