package br.com.filipecode.DeskhelpApi.usuario.dto.response;

import br.com.filipecode.DeskhelpApi.usuario.enums.Role;

import java.util.UUID;

public record UsuarioRespostaDTO(UUID id,
                                 String nome,
                                 String email,
                                 Role role,
                                 String departamento,
                                 String cargo) {
}
