package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.entities.Auditoria;
import br.com.filipecode.DeskhelpApi.model.entities.Chamado;
import br.com.filipecode.DeskhelpApi.repositories.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
