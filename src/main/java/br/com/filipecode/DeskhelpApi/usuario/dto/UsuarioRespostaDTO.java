package br.com.filipecode.DeskhelpApi.usuario.dto;

import java.util.UUID;

public record UsuarioRespostaDTO(UUID id,
                                 String nome,
                                 String email,
                                 String departamento,
                                 String cargo) {
}
