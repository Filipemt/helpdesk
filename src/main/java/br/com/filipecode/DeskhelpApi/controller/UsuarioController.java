package br.com.filipecode.DeskhelpApi.controller;

import br.com.filipecode.DeskhelpApi.model.dtos.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.UsuarioRespostaDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
import br.com.filipecode.DeskhelpApi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

        if (usuarioRespostaDTO.isPresent()) {
            return ResponseEntity.ok(usuarioRespostaDTO);
        }

        return ResponseEntity.notFound().build();
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
        Optional<Usuario> possivelUsuario = usuarioService.listarPorId(usuarioId);

        if (possivelUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deletarPorId(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
