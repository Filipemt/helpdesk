package br.com.filipecode.DeskhelpApi.controller;

import br.com.filipecode.DeskhelpApi.model.entities.Auditoria;
import br.com.filipecode.DeskhelpApi.repositories.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

    private final AuditoriaRepository auditoriaRepository;

    @GetMapping
    public List<Auditoria> listarAuditorias(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) UUID tecnicoId,
            @RequestParam(required = false) UUID usuarioId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        List<Auditoria> auditorias = auditoriaRepository.findAll();

        return auditorias.stream()
                .filter(a -> status == null || a.getStatus().name().equalsIgnoreCase(status))
                .filter(a -> tecnicoId == null || tecnicoId.equals(a.getTecnicoId()))
                .filter(a -> usuarioId == null || usuarioId.equals(a.getUsuarioId()))
                .filter(a -> dataInicial == null || !a.getDataEvento().isBefore(dataInicial))
                .filter(a -> dataFinal == null || !a.getDataEvento().isAfter(dataFinal))
                .toList();
    }
}
