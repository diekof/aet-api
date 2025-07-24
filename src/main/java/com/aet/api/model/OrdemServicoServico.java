package com.aet.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordem_servico_servico")
public class OrdemServicoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordem_servico_servico_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "tipo_servico_id", nullable = false)
    private TipoServico tipoServico;

    @Column(name = "ordem_servico_serv_vlr_uni", nullable = false, precision = 17, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "ordem_servico_serv_obs", length = 400)
    private String observacao;

    @Column(name = "ordem_servico_serv_status", nullable = false)
    private Short status;

    @Column(name = "ordem_servico_serv_vlr_desc", precision = 17, scale = 2)
    private BigDecimal valorDesconto;

    @Column(name = "ordem_servico_serv_numero")
    private Long numero;

    @Column(name = "ordem_servico_serv_numart")
    private Long numeroArt;

    @Column(name = "ordem_servico_serv_dtalib")
    private LocalDate dataLiberacao;

    @Column(name = "ordem_servico_serv_dtavenc")
    private LocalDate dataVencimento;

    @Column(name = "ordem_servico_gerada")
    private Integer ordemServicoGerada;

    @Column(name = "ordem_servico_servico_gerada")
    private Integer servicoGerado;

    @Column(name = "ordem_servico_data_ult_email")
    private LocalDate dataUltimoEmail;

    // Getters e Setters (ou anotações do Lombok como @Data/@Getter/@Setter)
}
