package br.com.filipecode.DeskhelpApi.shared.dto;

import java.time.Instant;

public record ErroPadronizadoDTO(
        int status,
        String erro,
        String mensagem,
        String caminho,
        String timestamp) {
}
