package br.com.filipecode.DeskhelpApi.chamado.repository;

import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.chamado.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.chamado.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChamadoRepository extends JpaRepository<Chamado, UUID> {
    List<Chamado> findByTitulo(String titulo);
    List<Chamado> findByStatus(Status status);
    List<Chamado> findByPrioridade(Prioridade prioridade);
    List<Chamado> findByTituloAndStatusAndPrioridade(String titulo, Status status, Prioridade prioridade);
}
