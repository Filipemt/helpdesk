package br.com.filipecode.DeskhelpApi.usuario.validator;

import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RegistroDuplicadoException;

import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public void validarEmailDuplicado(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RegistroDuplicadoException("Já existe um usuário cadastrado com o email informado!");
        }
    }

    public void validarUsuarioExiste(UUID id) {
         usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));
    }
}
