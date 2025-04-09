package br.com.filipecode.DeskhelpApi.chamado.dto;

import br.com.filipecode.DeskhelpApi.shared.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.shared.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChamadoRespostaDTO(
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