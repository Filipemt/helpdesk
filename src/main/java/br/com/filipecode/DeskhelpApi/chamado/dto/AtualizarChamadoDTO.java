package br.com.filipecode.DeskhelpApi.chamado.dto;

import java.util.UUID;

public record AtualizarChamadoDTO(String status, String prioridade, UUID tecnicoID) {
}
