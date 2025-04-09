package br.com.filipecode.DeskhelpApi.tecnico.controller;

import br.com.filipecode.DeskhelpApi.tecnico.dto.TecnicoDTO;
import br.com.filipecode.DeskhelpApi.tecnico.dto.TecnicoRespostaDTO;
import br.com.filipecode.DeskhelpApi.tecnico.entity.Tecnico;
import br.com.filipecode.DeskhelpApi.tecnico.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @PostMapping
    public ResponseEntity<Object> criarTecnico(@RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoService.criarTecnico(tecnicoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarTecnico(@PathVariable String id,
                                                       @RequestBody TecnicoDTO tecnicoDTO) {
        UUID tecnicoId = UUID.fromString(id);
        tecnicoService.atualizarTecnico(tecnicoId, tecnicoDTO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<TecnicoRespostaDTO>> buscarTecnicoPorId(@PathVariable String id) {
       UUID tecnicoId = UUID.fromString(id);

       tecnicoService.buscarDetalhesPorId(tecnicoId);
       return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TecnicoRespostaDTO>> buscarTodosOsUsuarios(@RequestParam(value = "especializacao", required = false) String especializacao) {

        List<TecnicoRespostaDTO> lista = tecnicoService.filtarTecnico(especializacao);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarTecnico(@PathVariable String id) {
        UUID tecnicoId = UUID.fromString(id);
        tecnicoService.deletarPorId(tecnicoId);

        return ResponseEntity.noContent().build();
    }
}
