package br.com.filipecode.DeskhelpApi.model.dtos;

import java.util.UUID;

public record TecnicoRespostaDTO(UUID id,
                                 String nome,
                                 String email,
                                 String especializacao) {
}
