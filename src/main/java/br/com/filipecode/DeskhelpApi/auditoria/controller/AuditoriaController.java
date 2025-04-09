package br.com.filipecode.DeskhelpApi.auditoria.controller;

import br.com.filipecode.DeskhelpApi.chamado.dto.HistoricoChamadoDTO;
import br.com.filipecode.DeskhelpApi.auditoria.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/auditorias")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<HistoricoChamadoDTO>> listarHistoricoAgrupadoComFiltro(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) UUID tecnicoId,
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        List<HistoricoChamadoDTO> historico = auditoriaService.listarHistoricoAgrupadoComFiltro(
                status, tecnicoId, usuarioId, dataInicial, dataFinal
        );
        return ResponseEntity.ok(historico);
    }
}
