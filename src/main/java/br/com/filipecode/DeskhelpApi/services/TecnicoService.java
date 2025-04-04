package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.dtos.TecnicoDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.TecnicoRespostaDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Tecnico;
import br.com.filipecode.DeskhelpApi.repositories.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public Tecnico criarTecnico(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = new Tecnico();
        tecnico.setNome(tecnicoDTO.nome());
        tecnico.setEmail(tecnicoDTO.email());
        tecnico.setEspecializacao(tecnicoDTO.especializacao());

        return tecnicoRepository.save(tecnico);
    }

    public TecnicoRespostaDTO atualizarTecnico(UUID id, TecnicoDTO tecnicoDTO) {
        return tecnicoRepository.findById(id)
                .map(tecnico -> {
                    tecnico.setNome(tecnicoDTO.nome());
                    tecnico.setEmail(tecnicoDTO.email());
                    tecnico.setEspecializacao(tecnicoDTO.especializacao());
                    tecnicoRepository.save(tecnico);

                    return new TecnicoRespostaDTO(
                            tecnico.getId(),
                            tecnico.getNome(),
                            tecnico.getEmail(),
                            tecnico.getEspecializacao()
                    );
                })
                .orElseThrow(() -> new RuntimeException("Tecnico não encontrado!"));
    }

    public Optional<Tecnico> listarPorId(UUID id) {
        return tecnicoRepository.findById(id);
    }

    public Optional<TecnicoRespostaDTO> buscarDetalhesPorId(UUID id) {
        return tecnicoRepository.findById(id)
                .map(tecnico -> new TecnicoRespostaDTO(
                        tecnico.getId(),
                        tecnico.getNome(),
                        tecnico.getEmail(),
                        tecnico.getEspecializacao()
                ));
    }

    public List<TecnicoRespostaDTO> filtarTecnico(String especializacao) {
        List<Tecnico> tecnicos;

        if (especializacao != null) {
            tecnicos = tecnicoRepository.findByEspecializacao(especializacao);
        } else {
            tecnicos = tecnicoRepository.findAll();
        }

            return tecnicos.stream()
                    .map(tecnico -> new TecnicoRespostaDTO(
                            tecnico.getId(),
                            tecnico.getNome(),
                            tecnico.getEmail(),
                            tecnico.getEspecializacao()
                    ))
                    .collect(Collectors.toList());

    }

    public void deletarPorId(UUID id) {
        tecnicoRepository.deleteById(id);
    }
}
