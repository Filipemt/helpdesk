package br.com.filipecode.DeskhelpApi.chamado.dto;

import br.com.filipecode.DeskhelpApi.chamado.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.UUID;

public record ChamadoDTO(
        @NotBlank(message = "Titulo é obrigatório!") String titulo,
        String descricao,
        Prioridade prioridade,
        @NotNull(message = "Usuário é obrigatório!") UUID usuarioId
) {
}
