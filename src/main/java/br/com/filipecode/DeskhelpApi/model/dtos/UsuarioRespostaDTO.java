package br.com.filipecode.DeskhelpApi.model.dtos;

import java.util.UUID;

public record UsuarioRespostaDTO(UUID id,
                                 String nome,
                                 String email,
                                 String departamento,
                                 String cargo) {
}
