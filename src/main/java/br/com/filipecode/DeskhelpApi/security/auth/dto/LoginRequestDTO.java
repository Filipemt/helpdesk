package br.com.filipecode.DeskhelpApi.security.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @NotBlank(message = "Email obrigatório") String email,
        @NotBlank(message = "Senha é obrigatória") @Size(message = "A senha deve ter no mínimo 6 caracteres") String senha)
{
}
