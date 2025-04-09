package br.com.filipecode.DeskhelpApi.chamado.dto;

import java.time.LocalDateTime;

public record EventoChamadoDTO(String status, String descricaoEvento, LocalDateTime dataEvento) {
}
