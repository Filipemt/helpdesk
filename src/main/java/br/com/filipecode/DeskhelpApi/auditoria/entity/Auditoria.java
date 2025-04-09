package br.com.filipecode.DeskhelpApi.auditoria.entity;

import br.com.filipecode.DeskhelpApi.shared.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auditoria_chamados")
@Getter
@Setter
@NoArgsConstructor
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID chamadoId;
    private String tituloChamado;

    private UUID tecnicoId;
    private String nomeTecnico;

    private UUID usuarioId;
    private String nomeUsuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dataEvento;
    private String descricaoEvento;
}
