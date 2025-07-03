package br.com.filipecode.DeskhelpApi.legacy.tecnico.dto;

import jakarta.validation.constraints.NotBlank;

public record TecnicoDTO(
                        @NotBlank(message = "Nome é obrigatório!") String nome,
                        @NotBlank(message = "Email é obrigatório!") String email,
                        @NotBlank(message = "Especialização é obrigatório!") String especializacao) {
}
