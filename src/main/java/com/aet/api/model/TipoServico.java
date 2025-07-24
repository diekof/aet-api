package com.aet.api.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_servico_id")
    private Long id;

    @Column(name = "tipo_servico_nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "tipo_servico_estado", nullable = false, length = 120)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "tipo_veiculo_id", nullable = false)
    private TipoVeiculo tipoVeiculo;

    @Column(name = "tipo_servico_valor", nullable = false, precision = 17, scale = 2)
    private BigDecimal valor;

    @Column(name = "tipo_servico_reboque", nullable = false, length = 120)
    private String reboque;

    @Column(name = "tipo_servico_ativo")
    private Boolean ativo;

    @Column(name = "tipo_servico_desp_valor", precision = 17, scale = 2)
    private BigDecimal despesaValor;

    // Getters e Setters (ou anotações do Lombok como @Data/@Getter/@Setter)
}