package br.com.filipecode.DeskhelpApi.chamado.dto;

import br.com.filipecode.DeskhelpApi.shared.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ChamadoDTO(
        String titulo,
        String descricao,
        Prioridade prioridade,
        UUID usuarioId
) {
}
