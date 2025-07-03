package br.com.filipecode.DeskhelpApi.legacy.tecnico.controller;

import br.com.filipecode.DeskhelpApi.legacy.tecnico.dto.TecnicoDTO;
import br.com.filipecode.DeskhelpApi.legacy.tecnico.dto.TecnicoRespostaDTO;
import br.com.filipecode.DeskhelpApi.legacy.tecnico.entity.Tecnico;
import br.com.filipecode.DeskhelpApi.legacy.tecnico.service.TecnicoService;
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


@RequestMapping("tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @Operation(
            summary = "Criação de técnicos",
            description = "Cria um novo técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Técnico criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro duplicado")
    })
    @PostMapping
    public ResponseEntity<Object> criarTecnico(@RequestBody @Valid TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoService.criarTecnico(tecnicoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(
            summary = "Atualização de técnicos",
            description = "Atualiza técnico pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Técnico criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarTecnico(@PathVariable String id,
                                                   @RequestBody @Valid TecnicoDTO tecnicoDTO) {
        UUID tecnicoId = UUID.fromString(id);
        tecnicoService.atualizarTecnico(tecnicoId, tecnicoDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Busca técnico por ID",
            description = "Busca técnico por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<Optional<TecnicoRespostaDTO>> buscarTecnicoPorId(@PathVariable String id) {
       UUID tecnicoId = UUID.fromString(id);

       Optional<TecnicoRespostaDTO> tecnicoRespostaDTO = tecnicoService.buscarDetalhesPorId(tecnicoId);
       return ResponseEntity.ok(tecnicoRespostaDTO);
    }

    @Operation(
            summary = "Filtra usuários baseado por especialização",
            description = "Filtra usuários baseado por especialização, caso não seja passado parâmetro, lista todos os técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<TecnicoRespostaDTO>> buscarTodosOsUsuarios(@RequestParam(value = "especializacao", required = false) String especializacao) {

        List<TecnicoRespostaDTO> lista = tecnicoService.filtarTecnico(especializacao);
        return ResponseEntity.ok(lista);
    }

    @Operation(
            summary = "Deleção de técnicos",
            description = "Deletar técnico por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Técnico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tecnico não encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarTecnico(@PathVariable String id) {
        UUID tecnicoId = UUID.fromString(id);
        tecnicoService.deletarPorId(tecnicoId);

        return ResponseEntity.noContent().build();
    }
}
