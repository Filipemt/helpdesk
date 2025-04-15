package br.com.filipecode.DeskhelpApi.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
                         @NotBlank(message = "Nome é obrigatório!") String nome,
                         @NotBlank(message = "Email é obrigatório!") String email,
                         @NotBlank(message = "Departamento é obrigatório!") String departamento,
                         @NotBlank(message = "Cargo é obrigatório!") String cargo) {
}
