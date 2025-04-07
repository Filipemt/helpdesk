package br.com.filipecode.DeskhelpApi.controller;

import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoRespostaDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Chamado;
import br.com.filipecode.DeskhelpApi.repositories.ChamadoRepository;
import br.com.filipecode.DeskhelpApi.services.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<Optional<ChamadoRespostaDTO>> buscarChamadoPorId(@PathVariable String id) {
        UUID chamadoId = UUID.fromString(id);
        Optional<ChamadoRespostaDTO> possivelChamado = chamadoService.buscarDetalhesPorId(chamadoId);

        if (possivelChamado.isPresent()) {
            return ResponseEntity.ok(possivelChamado);
        }

        return ResponseEntity.notFound().build();
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
