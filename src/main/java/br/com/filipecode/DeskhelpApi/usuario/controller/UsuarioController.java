package br.com.filipecode.DeskhelpApi.usuario.controller;

import br.com.filipecode.DeskhelpApi.usuario.dto.request.AtualizarUsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.request.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.usuario.dto.response.UsuarioRespostaDTO;
import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(
            summary = "Criação de usuário",
            description = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro duplicado!")
    })
    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioRespostaDTO usuario = usuarioService.salvarUsuario(usuarioDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.id())
                .toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @PostMapping("/tecnicos")
    public ResponseEntity<UsuarioRespostaDTO> criarTecnico(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioRespostaDTO tecnico = usuarioService.salvarTecnico(usuarioDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.id())
                .toUri();

        return ResponseEntity.created(uri).body(tecnico);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable String id,
                                                   @RequestBody @Valid AtualizarUsuarioDTO dto) {
        UUID usuarioId = UUID.fromString(id);
        usuarioService.atualizarUsuario(usuarioId, dto);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Busca usuário pelo Id",
            description = "Busca usuário pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!")
    })
    @GetMapping("{id}")
    public ResponseEntity<Optional<UsuarioRespostaDTO>> buscarUsuarioPorId(@PathVariable String id) {
        UUID usuarioId = UUID.fromString(id);
        Optional<UsuarioRespostaDTO> usuarioRespostaDTO = usuarioService.buscarDetalhesPorId(usuarioId);

        return ResponseEntity.ok(usuarioRespostaDTO);

    }

    @Operation(
            summary = "Filtra usuários baseados nome ou departamento",
            description = "Filtra usuários pelo nome ou departamento, caso não seja passado nenhum parâmetro, lista todos os usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> buscarTodosOsUsuarios(@RequestParam(value = "nome", required = false) String nome,
                                                                          @RequestParam(value = "departamento", required = false) String departamento) {

        List<UsuarioRespostaDTO> lista = usuarioService.filtrarUsuario(nome, departamento);
        return ResponseEntity.ok(lista);
    }

    @Operation(
            summary = "Deleção de usuário",
            description = "Deletar usuário pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        UUID usuarioId = UUID.fromString(id);

        usuarioService.listarPorId(usuarioId);
        usuarioService.deletarPorId(usuarioId);

        return ResponseEntity.noContent().build();
    }
}
