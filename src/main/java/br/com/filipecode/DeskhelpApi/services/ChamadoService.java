package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.dtos.ChamadoDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Chamado;
import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
import br.com.filipecode.DeskhelpApi.model.enums.Status;
import br.com.filipecode.DeskhelpApi.repositories.ChamadoRepository;
import br.com.filipecode.DeskhelpApi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

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
}
