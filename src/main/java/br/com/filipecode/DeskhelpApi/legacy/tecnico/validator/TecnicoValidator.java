package br.com.filipecode.DeskhelpApi.legacy.tecnico.validator;

import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;
import br.com.filipecode.DeskhelpApi.legacy.tecnico.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TecnicoValidator {

    private final TecnicoRepository tecnicoRepository;

    public void validarEmailDuplicado(String email) {
        if (tecnicoRepository.existsByEmail(email)) {
            throw new RegistroDuplicadoException("Já existe um tecnico cadastrado com o email informado!");
        }
    }

    public void validarTecnicoExiste(UUID id) {
        tecnicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Técnico não encontrado!"));
    }
}
