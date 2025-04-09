package br.com.filipecode.DeskhelpApi.auditoria.repository;

import br.com.filipecode.DeskhelpApi.auditoria.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditoriaRepository extends JpaRepository<Auditoria, UUID> {
}