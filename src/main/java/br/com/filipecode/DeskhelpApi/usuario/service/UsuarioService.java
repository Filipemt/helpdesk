package br.com.filipecode.DeskhelpApi.usuario.service;

import br.com.filipecode.DeskhelpApi.usuario.dto.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.UsuarioRespostaDTO;
import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import br.com.filipecode.DeskhelpApi.usuario.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;

    public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setDepartamento(usuarioDTO.departamento());
        usuario.setCargo(usuarioDTO.cargo());

        usuarioValidator.validarEmailDuplicado(usuarioDTO.email());
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

    public Optional<Usuario> listarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioRespostaDTO> buscarDetalhesPorId(UUID id) {
         return usuarioRepository.findById(id)
                 .map(usuario -> new UsuarioRespostaDTO(
                         usuario.getId(),
                         usuario.getNome(),
                         usuario.getEmail(),
                         usuario.getDepartamento(),
                         usuario.getCargo()
                 ));
    }

    public List<UsuarioRespostaDTO> filtrarUsuario(String nome, String departamento) {
        List<Usuario> usuarios;

        if (nome != null && departamento != null) {
            usuarios = usuarioRepository.findByNomeAndDepartamento(nome, departamento);
        } else if (nome != null) {
            usuarios = usuarioRepository.findByNome(nome);
        } else if (departamento != null) {
            usuarios = usuarioRepository.findByDepartamento(departamento);
        } else {
            usuarios = usuarioRepository.findAll();
        }
        return usuarios.stream()
                .map(usuario -> new UsuarioRespostaDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDepartamento(),
                        usuario.getCargo()
                ))
                .collect(Collectors.toList());
    }
}
