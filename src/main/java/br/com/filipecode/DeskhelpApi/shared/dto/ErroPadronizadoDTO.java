package br.com.filipecode.DeskhelpApi.shared.dto;

public record ErroPadronizadoDTO(
        int status,
        String erro,
        String mensagem,
        String caminho,
        String timestamp) {
}
