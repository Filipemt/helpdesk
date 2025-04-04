package br.com.filipecode.DeskhelpApi.repositories;


import br.com.filipecode.DeskhelpApi.model.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TecnicoRepository extends JpaRepository<Tecnico, UUID> {
    List<Tecnico> findByEspecializacao(String especializacao);
}
