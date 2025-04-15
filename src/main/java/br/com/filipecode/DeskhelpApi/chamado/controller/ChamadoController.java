package br.com.filipecode.DeskhelpApi.chamado.controller;

import br.com.filipecode.DeskhelpApi.chamado.dto.AtualizarChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoRespostaDTO;
import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.chamado.service.ChamadoService;
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

    @PostMapping
    public ResponseEntity<Void> criarChamado(@RequestBody ChamadoDTO chamadoDTO) {
        chamadoService.criarChamado(chamadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ChamadoRespostaDTO>> filtrarChamados(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade
    ) {
        List<ChamadoRespostaDTO> chamados = chamadoService.filtrarChamados(titulo, status, prioridade);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ChamadoRespostaDTO>> buscarChamadoPorId(@PathVariable String id) {
        UUID chamadoId = UUID.fromString(id);
        Optional<ChamadoRespostaDTO> possivelChamado = chamadoService.buscarDetalhesPorId(chamadoId);

        if (possivelChamado.isPresent()) {
            return ResponseEntity.ok(possivelChamado);
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> atualizarChamado(@PathVariable UUID id,
                                                 @RequestBody AtualizarChamadoDTO atualizarChamadoDTO) {

        chamadoService.atualizarChamadoParcial(id, atualizarChamadoDTO);
        return ResponseEntity.noContent().build();
    }

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
