package br.com.filipecode.DeskhelpApi.chamado.validator;

import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.chamado.repository.ChamadoRepository;

import br.com.filipecode.DeskhelpApi.chamado.enums.Status;
import br.com.filipecode.DeskhelpApi.shared.exceptions.EntidadeNaoEncontradaException;
import br.com.filipecode.DeskhelpApi.shared.exceptions.RequisicaoInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChamadoValidador {

    private final ChamadoRepository chamadoRepository;

    public Chamado validarChamadoPodeSerAlterado(UUID id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Chamado não encontrado!"));

        if (chamado.getStatus() == Status.CONCLUIDO) {
            throw new RequisicaoInvalidaException("Chamado já foi concluido e não pode ser alterado!");
        }

        return chamado;
    }
}
