package com.aet.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_veiculo")
public class TipoVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_veiculo_id")
    private Long id;

    @Column(name = "tipo_veiculo_nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "tipo_veiculo_descricao", nullable = false, length = 400)
    private String descricao;

    @Column(name = "tipo_veiculo_ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "tipo_veiculo_data_cricao")
    private LocalDateTime dataCriacao;

    @Column(name = "tipo_veiculo_data_alteracao")
    private LocalDateTime dataAlteracao;

    // Getters e Setters (ou use @Data, @Getter/@Setter do Lombok)
}