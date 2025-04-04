package br.com.filipecode.DeskhelpApi.model.dtos;

import br.com.filipecode.DeskhelpApi.model.enums.Prioridade;

import java.util.UUID;

public record ChamadoDTO(String titulo,
                         String descricao,
                         Prioridade prioridade,
                         UUID usuarioId) {
}
