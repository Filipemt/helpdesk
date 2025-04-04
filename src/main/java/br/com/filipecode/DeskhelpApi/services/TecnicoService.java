package br.com.filipecode.DeskhelpApi.services;

import br.com.filipecode.DeskhelpApi.model.dtos.TecnicoDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.TecnicoRespostaDTO;
import br.com.filipecode.DeskhelpApi.model.dtos.UsuarioDTO;
import br.com.filipecode.DeskhelpApi.model.entities.Tecnico;
import br.com.filipecode.DeskhelpApi.model.entities.Usuario;
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

    public Optional<Tecnico> listarPorId(UUID id) {
        return tecnicoRepository.findById(id);
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

    public Tecnico atualizarTecnico(UUID id, TecnicoDTO tecnicoDTO) {
        Optional<Tecnico> tecnicoExistente = tecnicoRepository.findById(id);

        if (tecnicoExistente.isPresent()) {
            Tecnico tecnico = new Tecnico();
            tecnico.setNome(tecnicoDTO.nome());
            tecnico.setEmail(tecnicoDTO.email());
            tecnico.setEspecializacao(tecnicoDTO.especializacao());

            return tecnicoRepository.save(tecnico);
        } else {
            throw new RuntimeException("Usuário não encontrado! ");
        }
    }

    public void deletarPorId(UUID id) {
        tecnicoRepository.deleteById(id);
    }
}
