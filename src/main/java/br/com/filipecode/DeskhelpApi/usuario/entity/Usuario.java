package br.com.filipecode.DeskhelpApi.usuario.entity;

import br.com.filipecode.DeskhelpApi.chamado.entity.Chamado;
import br.com.filipecode.DeskhelpApi.usuario.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "usuario")
    private List<Chamado> chamados = new ArrayList<>();

}
