package br.com.filipecode.DeskhelpApi.usuario.dto.request;

import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(
        @NotBlank(message = "Nome é obrigatório!") String nome,
        @NotBlank(message = "Email é obrigatório!") String email,
        @NotNull(message = "Role de atualização é obrigatória") Role role,
        @NotBlank(message = "Departamento é obrigatório!") String departamento,
        @NotBlank(message = "Cargo é obrigatório!") String cargo)
 {

}
