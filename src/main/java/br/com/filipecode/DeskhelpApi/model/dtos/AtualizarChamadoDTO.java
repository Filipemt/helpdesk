package br.com.filipecode.DeskhelpApi.model.dtos;

import java.util.UUID;

public record AtualizarChamadoDTO(String status, String prioridade, UUID tecnicoID) {
}
