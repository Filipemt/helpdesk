package br.com.filipecode.DeskhelpApi.model.dtos;

import java.util.List;
import java.util.UUID;

public record HistoricoChamadoDTO(UUID chamadoId, String tituloChamado, List<EventoChamadoDTO> historico) {
}
