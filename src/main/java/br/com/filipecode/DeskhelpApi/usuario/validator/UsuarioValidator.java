package br.com.filipecode.DeskhelpApi.usuario.validator;

import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;

import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public void validarEmailDuplicado(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RegistroDuplicadoException("Já existe um usuário cadastrado com o email informado!");
        }
    }
}
