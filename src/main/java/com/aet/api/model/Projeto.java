package com.aet.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projeto_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_servico_id")
    private OrdemServicoServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "projeto_engenheiro_id", nullable = false)
    private Cliente engenheiro;

    @Column(name = "projeto_num_eixos", nullable = false)
    private Short numEixos;

    @Column(name = "projeto_Peso_Bruto", nullable = false)
    private Long pesoBruto;

    @Column(name = "projeto_altura_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal alturaTotal;

    @Column(name = "projeto_comprimento_veiculo", nullable = false, precision = 9, scale = 2)
    private BigDecimal comprimentoVeiculo;

    @Column(name = "projeto_excesso_dianteiro", nullable = false, precision = 9, scale = 2)
    private BigDecimal excessoDianteiro;

    @Column(name = "projeto_excesso_traseiro", nullable = false, precision = 9, scale = 2)
    private BigDecimal excessoTraseiro;

    @Column(name = "projeto_largura_veiculo", nullable = false, precision = 9, scale = 2)
    private BigDecimal larguraVeiculo;

    @Column(name = "projeto_Excesso_direita", nullable = false, precision = 9, scale = 2)
    private BigDecimal excessoDireita;

    @Column(name = "projeto_Excesso_esquerda", nullable = false, precision = 9, scale = 2)
    private BigDecimal excessoEsquerda;

    @Column(name = "projetocarretas_principais_ult", nullable = false)
    private Long carretasPrincipaisUlt;

    @Column(name = "projetoEixos_Ultimo", nullable = false)
    private Long eixosUltimo;

    @Column(name = "projetocarretas_rodizio_ultimo", nullable = false)
    private Long carretasRodizioUltimo;

    @Lob
    @Column(name = "projeto_foto_planta_A", nullable = false)
    private byte[] fotoPlantaA;

    @Lob
    @Column(name = "projeto_foto_planta_A2", nullable = false)
    private byte[] fotoPlantaA2;

    @Column(name = "projeto_dim_comb_A", precision = 9, scale = 3)
    private BigDecimal dimCombA;

    @Column(name = "projeto_PCM")
    private Long pcm;

    @Column(name = "projeto_PSR1")
    private Long psr1;

    @Column(name = "projeto_PSR2")
    private Long psr2;

    @Column(name = "projeto_PBT_CM")
    private Long pbtCm;

    @Column(name = "projeto_CL_KG")
    private Integer clKg;

    @Column(name = "projeto_RP1_KG")
    private Integer rp1Kg;

    @Column(name = "projeto_PDolly", precision = 9, scale = 3)
    private BigDecimal pDolly;

    @Column(name = "projeto_PBTC_kg")
    private Long pbtcKg;

    @Column(name = "projeto_PBTC_Total")
    private Integer pbtcTotal;

    @ManyToOne
    @JoinColumn(name = "Combinacao_veicular_id")
    private CombinacaoVeicular combinacaoVeicular;

    @Column(name = "projeto_comprimento", precision = 5, scale = 2)
    private BigDecimal comprimento;

    // Getters e Setters omitidos para brevidade (pode-se usar Lombok se preferir)

}
