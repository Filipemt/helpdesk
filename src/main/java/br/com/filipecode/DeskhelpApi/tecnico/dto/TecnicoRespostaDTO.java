package br.com.filipecode.DeskhelpApi.tecnico.dto;

import java.util.UUID;

public record TecnicoRespostaDTO(UUID id,
                                 String nome,
                                 String email,
                                 String especializacao) {
}
