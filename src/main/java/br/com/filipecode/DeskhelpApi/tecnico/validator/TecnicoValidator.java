package br.com.filipecode.DeskhelpApi.tecnico.validator;

import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;
import br.com.filipecode.DeskhelpApi.tecnico.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TecnicoValidator {

    private final TecnicoRepository tecnicoRepository;

    public void validarEmailDuplicado(String email) {
        if (tecnicoRepository.existsByEmail(email)) {
            throw new RegistroDuplicadoException("Já existe um tecnico cadastrado com o email informado!");
        }
    }
}
