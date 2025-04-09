package br.com.filipecode.DeskhelpApi.auditoria.service;

import br.com.filipecode.DeskhelpApi.chamado.dto.EventoChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.HistoricoChamadoDTO;
import br.com.filipecode.DeskhelpApi.auditoria.entity.Auditoria;
import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.auditoria.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public void registrarEvento(Chamado chamado, String descricaoEvento) {
        Auditoria auditoria = new Auditoria();
        auditoria.setChamadoId(chamado.getId());
        auditoria.setTituloChamado(chamado.getTitulo());

        auditoria.setUsuarioId(chamado.getUsuario().getId());
        auditoria.setNomeUsuario(chamado.getUsuario().getNome());

        if (chamado.getTecnico() != null) {
            auditoria.setTecnicoId(chamado.getTecnico().getId());
            auditoria.setNomeTecnico(chamado.getTecnico().getNome());
        }

        auditoria.setStatus(chamado.getStatus());
        auditoria.setDescricaoEvento(descricaoEvento);
        auditoria.setDataEvento(LocalDateTime.now());

        auditoriaRepository.save(auditoria);
    }

    public List<HistoricoChamadoDTO> listarHistoricoAgrupadoComFiltro(
            String status,
            UUID tecnicoId,
            UUID usuarioId,
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    ) {
        List<Auditoria> auditorias = auditoriaRepository.findAll();

        // Aplica os filtros antes de agrupar
        List<Auditoria> filtradas = auditorias.stream()
                .filter(a -> status == null || a.getStatus().name().equalsIgnoreCase(status))
                .filter(a -> tecnicoId == null || tecnicoId.equals(a.getTecnicoId()))
                .filter(a -> usuarioId == null || usuarioId.equals(a.getUsuarioId()))
                .filter(a -> dataInicial == null || !a.getDataEvento().isBefore(dataInicial))
                .filter(a -> dataFinal == null || !a.getDataEvento().isAfter(dataFinal))
                .toList();

        return filtradas.stream()
                .collect(Collectors.groupingBy(Auditoria::getChamadoId))
                .entrySet().stream()
                .map(entry -> {
                    UUID chamadoId = entry.getKey();
                    List<Auditoria> eventos = entry.getValue();

                    String tituloChamado = eventos.get(0).getTituloChamado();

                    List<EventoChamadoDTO> historico = eventos.stream()
                            .map(a -> new EventoChamadoDTO(
                                    a.getStatus().name(),
                                    a.getDescricaoEvento(),
                                    a.getDataEvento()
                            ))
                            .sorted(Comparator.comparing(EventoChamadoDTO::dataEvento))
                            .collect(Collectors.toList());

                    return new HistoricoChamadoDTO(chamadoId, tituloChamado, historico);
                })
                .collect(Collectors.toList());
    }
}
