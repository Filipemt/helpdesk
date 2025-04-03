package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.dtos.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
import br.com.filipecode.DeskhelpApi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setDepartamento(usuarioDTO.departamento());
        usuario.setCargo(usuarioDTO.cargo());

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(UUID id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setNome(usuarioDTO.nome());
            usuario.setEmail(usuarioDTO.email());
            usuario.setDepartamento(usuarioDTO.departamento());
            usuario.setCargo(usuarioDTO.cargo());

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado! ");
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
