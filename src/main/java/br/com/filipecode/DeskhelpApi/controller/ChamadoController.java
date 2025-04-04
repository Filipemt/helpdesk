package br.com.filipecode.DeskhelpApi.controller;

import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.services.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
