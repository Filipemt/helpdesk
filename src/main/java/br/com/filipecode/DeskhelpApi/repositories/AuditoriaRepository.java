package br.com.filipecode.DeskhelpApi.repositories;

import br.com.filipecode.DeskhelpApi.model.entities.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditoriaRepository extends JpaRepository<Auditoria, UUID> {
}