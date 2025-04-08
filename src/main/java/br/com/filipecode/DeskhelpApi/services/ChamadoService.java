package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.dtos.AtualizarChamadoDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoRespostaDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Chamado;
import br.com.filipecode.DeskhelpApi.model.entities.Tecnico;
import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
import br.com.filipecode.DeskhelpApi.model.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.model.enums.Status;
import br.com.filipecode.DeskhelpApi.repositories.ChamadoRepository;
import br.com.filipecode.DeskhelpApi.repositories.TecnicoRepository;
import br.com.filipecode.DeskhelpApi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TecnicoRepository tecnicoRepository;

    public void criarChamado(ChamadoDTO chamadoDTO) {
        Usuario usuario = usuarioRepository.findById(chamadoDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Chamado chamado = new Chamado();
        chamado.setTitulo(chamadoDTO.titulo());
        chamado.setDescricao(chamadoDTO.descricao());
        chamado.setStatus(Status.ABERTO);
        chamado.setPrioridade(chamadoDTO.prioridade());

        LocalDateTime agora = LocalDateTime.now();
        chamado.setDataCriacao(agora);
        chamado.setDataAtualizacao(agora);

        chamado.setUsuario(usuario);

        chamadoRepository.save(chamado);
    }

    public Optional<Chamado> listarChamadoPorId(UUID id) {
        return chamadoRepository.findById(id);
    }

    public Optional<ChamadoRespostaDTO> buscarDetalhesPorId(UUID id) {
        return chamadoRepository.findById(id)
                .map(chamado -> new ChamadoRespostaDTO(
                        chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        chamado.getDataCriacao(),
                        chamado.getDataAtualizacao(),
                        chamado.getUsuario().getId(),
                        chamado.getTecnico() != null ? chamado.getTecnico().getId() : null
                ));
    }

    public List<ChamadoRespostaDTO> filtrarChamados(String titulo, String status, String prioridade) {
        Status statusEnum = (status != null) ? Status.valueOf(status) : null;
        Prioridade prioridadeEnum = (prioridade != null) ? Prioridade.valueOf(prioridade) : null;

        List<Chamado> chamados;

        if (titulo != null && statusEnum != null && prioridadeEnum != null) {
            chamados = chamadoRepository.findByTituloAndStatusAndPrioridade(titulo, statusEnum, prioridadeEnum);
        } else if (titulo != null) {
            chamados = chamadoRepository.findByTitulo(titulo);
        } else if (statusEnum != null) {
            chamados = chamadoRepository.findByStatus(statusEnum);
        } else if (prioridadeEnum != null) {
            chamados = chamadoRepository.findByPrioridade(prioridadeEnum);
        } else {
            chamados = chamadoRepository.findAll();
        }

        return chamados.stream()
                .map(chamado -> new ChamadoRespostaDTO(
                        chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        chamado.getDataCriacao(),
                        chamado.getDataAtualizacao(),
                        chamado.getUsuario().getId(),
                        chamado.getTecnico() != null ? chamado.getTecnico().getId() : null
                ))
                .collect(Collectors.toList());
    }

    public void deletarChamadoPorId(UUID id) {
        chamadoRepository.deleteById(id);
    }

    public void atualizarChamadoParcial(UUID id, AtualizarChamadoDTO atualizarChamadoDTO) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        if(atualizarChamadoDTO.status() != null) {
            chamado.setStatus(Status.valueOf(atualizarChamadoDTO.status()));
        }
        if (atualizarChamadoDTO.prioridade() != null) {
            chamado.setPrioridade(Prioridade.valueOf(atualizarChamadoDTO.prioridade()));
        }
        if (atualizarChamadoDTO.tecnicoID() != null) {
            Tecnico tecnico = tecnicoRepository.findById(atualizarChamadoDTO.tecnicoID())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
            chamado.setTecnico(tecnico);
        }

        chamado.setDataAtualizacao(LocalDateTime.now());

        chamadoRepository.save(chamado);
    }


}
