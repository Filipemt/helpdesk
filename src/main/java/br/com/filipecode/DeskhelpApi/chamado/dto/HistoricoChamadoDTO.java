package br.com.filipecode.DeskhelpApi.chamado.dto;

import java.util.List;
import java.util.UUID;

public record HistoricoChamadoDTO(UUID chamadoId, String tituloChamado, List<EventoChamadoDTO> historico) {
}
