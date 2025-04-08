package br.com.filipecode.DeskhelpApi.model.dtos;

import java.time.LocalDateTime;

public record EventoChamadoDTO(String status, String descricaoEvento, LocalDateTime dataEvento) {
}
