package com.aet.api.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordem_servico_id")
    private Long id;

    @Column(name = "ordem_servico_numero", nullable = false)
    private Short numero;

    @Column(name = "ordem_servico_status", nullable = false)
    private Short status;

    @Column(name = "ordem_servico_data_cria", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "ordem_servico_cli_cod", nullable = false)
    private Long clienteCodigo;

    @Column(name = "ordem_servico_cli_nom", nullable = false, length = 120)
    private String clienteNome;

    @Column(name = "ordem_servico_cli_pai_cod", nullable = false)
    private Long clientePaiCodigo;

    @Column(name = "ordem_servico_cli_pai_nom", nullable = false, length = 120)
    private String clientePaiNome;

    @ManyToOne
    @JoinColumn(name = "plano_pagamento_id", nullable = false)
    private PlanoPagamento planoPagamento;

    @Column(name = "ordem_servico_desc", nullable = false, length = 255)
    private String descricao;

    @Column(name = "ordem_servico_obs_pen", length = 255)
    private String observacaoPenal;

    @Column(name = "ordem_servico_obs_fin", length = 255)
    private String observacaoFinanceira;

    @Column(name = "ordem_servico_obs_serv", length = 255)
    private String observacaoServico;

    @Column(name = "ordem_servico_tipo")
    private Short tipo;

    @Column(name = "ordem_servico_entrada", precision = 17, scale = 2)
    private BigDecimal entrada;

    @Column(name = "ordem_servico_finId")
    private Integer financeiroId;

    @Column(name = "ordem_servico_data_fat")
    private LocalDate dataFaturamento;

    @Column(name = "ordem_servico_altera_pai")
    private Boolean alteraPai;

    // Getters e Setters (ou use @Data/@Getter/@Setter do Lombok)
}