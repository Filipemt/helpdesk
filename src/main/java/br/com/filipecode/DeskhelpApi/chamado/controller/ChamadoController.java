package br.com.filipecode.DeskhelpApi.chamado.controller;

import br.com.filipecode.DeskhelpApi.chamado.dto.AtualizarChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoRespostaDTO;
import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.chamado.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService chamadoService;

    @Operation(
            summary = "Abertura de um novo chamado",
            description = "Abre um novo chamado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Chamado criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<Void> criarChamado(@RequestBody @Valid ChamadoDTO chamadoDTO) {
        chamadoService.criarChamado(chamadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Filtrar chamados",
            description = "Filtra os chamados baseado no titulo, status e prioridade; Caso não seja passado nenhum parâmetro, retorna todos os chamados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamado retornado com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<ChamadoRespostaDTO>> filtrarChamados(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade
    ) {
        List<ChamadoRespostaDTO> chamados = chamadoService.filtrarChamados(titulo, status, prioridade);
        return ResponseEntity.ok(chamados);
    }

    @Operation(
            summary = "Buscar chamado por Id",
            description = "Busca chamado por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamado retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado"),
    })
    @GetMapping("{id}")
    public ResponseEntity<Optional<ChamadoRespostaDTO>> buscarChamadoPorId(@PathVariable String id) {
        UUID chamadoId = UUID.fromString(id);
        Optional<ChamadoRespostaDTO> possivelChamado = chamadoService.buscarDetalhesPorId(chamadoId);

        if (possivelChamado.isPresent()) {
            return ResponseEntity.ok(possivelChamado);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Atualização chamado por Id",
            description = "Atualiza chamado por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Chamado retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado"),
    })
    @PatchMapping("{id}")
    public ResponseEntity<Void> atualizarChamado(@PathVariable UUID id,
                                                 @RequestBody AtualizarChamadoDTO atualizarChamadoDTO) {

        chamadoService.atualizarChamadoParcial(id, atualizarChamadoDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deleção de chamado",
            description = "Deleta chamado por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Chamado deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarChamadoPorId(@PathVariable String id) {
        UUID chamadoId = UUID.fromString(id);
        Optional<Chamado> possivelChamado = chamadoService.listarChamadoPorId(chamadoId);

        if (possivelChamado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        chamadoService.deletarChamadoPorId(chamadoId);
        return ResponseEntity.noContent().build();

    }
}
