package br.com.filipecode.DeskhelpApi.usuario.controller;

import br.com.filipecode.DeskhelpApi.usuario.dto.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.UsuarioRespostaDTO;
import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.salvarUsuario(usuarioDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UsuarioRespostaDTO>> buscarUsuarioPorId(@PathVariable String id) {
        UUID usuarioId = UUID.fromString(id);
        Optional<UsuarioRespostaDTO> usuarioRespostaDTO = usuarioService.buscarDetalhesPorId(usuarioId);

        return ResponseEntity.ok(usuarioRespostaDTO);

    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> buscarTodosOsUsuarios(@RequestParam(value = "nome", required = false) String nome,
                                                                          @RequestParam(value = "departamento", required = false) String departamento) {

        List<UsuarioRespostaDTO> lista = usuarioService.filtrarUsuario(nome, departamento);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        UUID usuarioId = UUID.fromString(id);

        usuarioService.listarPorId(usuarioId);
        usuarioService.deletarPorId(usuarioId);

        return ResponseEntity.noContent().build();
    }
}
