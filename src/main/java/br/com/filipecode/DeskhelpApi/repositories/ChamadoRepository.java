package br.com.filipecode.DeskhelpApi.repositories;

import br.com.filipecode.DeskhelpApi.model.entities.Chamado;
import br.com.filipecode.DeskhelpApi.model.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChamadoRepository extends JpaRepository<Chamado, UUID> {
    List<Chamado> findByTitulo(String titulo);
    List<Chamado> findByStatus(Status status);
    List<Chamado> findByPrioridade(Prioridade prioridade);
}
