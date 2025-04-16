package br.com.filipecode.DeskhelpApi.auditoria.controller;

import br.com.filipecode.DeskhelpApi.chamado.dto.HistoricoChamadoDTO;
import br.com.filipecode.DeskhelpApi.auditoria.service.AuditoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Filtra histórico de chamados",
            description = "Lista histórico de chamados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtra histórico de chamados por status, tecnicoId, usuarioId, dataInicial e dataFinal, caso não seja passado nenhum parâmetro, lista todo histórico de chamados"),
    })
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
