package br.com.filipecode.DeskhelpApi.usuario.service;

import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.usuario.dto.request.AtualizarUsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.request.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.response.UsuarioRespostaDTO;
import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import br.com.filipecode.DeskhelpApi.usuario.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioValidator usuarioValidator;

    public UsuarioRespostaDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        usuarioValidator.validarEmailDuplicado(usuarioDTO.email());

        Usuario usuario = mapearParaEntidade(usuarioDTO, Role.USUARIO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return mapearParaRespostaDTO(usuarioSalvo);
    }

    public UsuarioRespostaDTO salvarTecnico(UsuarioDTO usuarioDTO) {
        usuarioValidator.validarEmailDuplicado(usuarioDTO.email());

        Usuario usuario = mapearParaEntidade(usuarioDTO, Role.TECNICO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return mapearParaRespostaDTO(usuarioSalvo);
    }

    public Usuario atualizarUsuario(UUID id, AtualizarUsuarioDTO atualizarUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        mapearParaAtualizacaoDTO(usuario, atualizarUsuarioDTO);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> listarPorId(UUID id) {
        usuarioValidator.validarUsuarioExiste(id);
        return usuarioRepository.findById(id);
    }

    public void deletarPorId(UUID id) {
        usuarioValidator.validarUsuarioExiste(id);
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioRespostaDTO> buscarDetalhesPorId(UUID id) {
        usuarioValidator.validarUsuarioExiste(id);
         return usuarioRepository.findById(id)
                 .map(this::mapearParaRespostaDTO);
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
                .map(this::mapearParaRespostaDTO)
                .collect(Collectors.toList());
    }

    private UsuarioRespostaDTO mapearParaRespostaDTO(Usuario usuario) {
        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getDepartamento(),
                usuario.getCargo()
        );
    }

    private Usuario mapearParaEntidade(UsuarioDTO usuarioDTO, Role role) {

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.nome());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setEmail(usuarioDTO.email());
        usuario.setRole(role);
        usuario.setDepartamento(usuarioDTO.departamento());
        usuario.setCargo(usuarioDTO.cargo());

        return usuario;
    }

    private void mapearParaAtualizacaoDTO(Usuario usuario, AtualizarUsuarioDTO atualizarUsuarioDTO) {
        usuario.setNome(atualizarUsuarioDTO.nome());
        usuario.setSenha(passwordEncoder.encode(atualizarUsuarioDTO.senha()));
        usuario.setEmail(atualizarUsuarioDTO.email());
        usuario.setRole(atualizarUsuarioDTO.role());
        usuario.setDepartamento(atualizarUsuarioDTO.departamento());
        usuario.setCargo(atualizarUsuarioDTO.cargo());
    }
}
