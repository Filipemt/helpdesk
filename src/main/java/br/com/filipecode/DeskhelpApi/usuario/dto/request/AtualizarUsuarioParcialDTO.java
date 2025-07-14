package br.com.filipecode.DeskhelpApi.usuario.dto.request;

import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AtualizarUsuarioParcialDTO(
        String nome,
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres") String senha,
        @Email(message = "Informe um e-mail válido") String email,
        Role role,
        String departamento,
        String cargo) {
}
