package br.com.filipecode.DeskhelpApi.model.dtos;

import br.com.filipecode.DeskhelpApi.model.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.model.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChamadoAtualizadoRespostaDTO(
        UUID id,
        String titulo,
        String descricao,
        Prioridade prioridade,
        Status status,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        UUID usuarioId,
        UUID tecnicoId
) {

}