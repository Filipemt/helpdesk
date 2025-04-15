package br.com.filipecode.DeskhelpApi.chamado.service;

import br.com.filipecode.DeskhelpApi.chamado.dto.AtualizarChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.chamado.dto.ChamadoRespostaDTO;
import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.auditoria.service.AuditoriaService;
import br.com.filipecode.DeskhelpApi.chamado.validator.ChamadoValidador;
import br.com.filipecode.DeskhelpApi.shared.enums.Prioridade;
import br.com.filipecode.DeskhelpApi.shared.enums.Status;
import br.com.filipecode.DeskhelpApi.tecnico.entity.Tecnico;
import br.com.filipecode.DeskhelpApi.usuario.entity.Usuario;
import br.com.filipecode.DeskhelpApi.chamado.repository.ChamadoRepository;
import br.com.filipecode.DeskhelpApi.tecnico.repository.TecnicoRepository;
import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
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
    private final AuditoriaService auditoriaService;
    private final ChamadoValidador chamadoValidador;

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

        auditoriaService.registrarEvento(
                chamado,
                "Chamado criado com status ABERTO."
        );
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
        Chamado chamado = chamadoValidador.validarChamadoPodeSerAlterado(id);
        StringBuilder descricaoEvento = new StringBuilder("Chamado atualizado: ");


        if(atualizarChamadoDTO.status() != null) {
            chamado.setStatus(Status.valueOf(atualizarChamadoDTO.status()));
            descricaoEvento.append("status alterado para ").append(atualizarChamadoDTO.status()).append(". ");

        }
        if (atualizarChamadoDTO.prioridade() != null) {
            chamado.setPrioridade(Prioridade.valueOf(atualizarChamadoDTO.prioridade()));
        }
        if (atualizarChamadoDTO.tecnicoID() != null) {
            Tecnico tecnico = tecnicoRepository.findById(atualizarChamadoDTO.tecnicoID())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
            chamado.setTecnico(tecnico);
            descricaoEvento.append("técnico atribuído: ").append(tecnico.getNome()).append(". ");

        }
        chamado.setDataAtualizacao(LocalDateTime.now());

        chamadoRepository.save(chamado);
        auditoriaService.registrarEvento(chamado, descricaoEvento.toString().trim());
    }
}
