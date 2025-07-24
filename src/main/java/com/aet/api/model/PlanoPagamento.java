package com.aet.api.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plano_pagamento")
public class PlanoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plano_pagamento_id")
    private Long id;

    @Column(name = "plano_pagamento_parcelas", nullable = false)
    private Short parcelas;

    @Column(name = "plano_pagamento_desc", nullable = false, length = 120)
    private String descricao;

    @Column(name = "plano_pagamento_ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "plano_pagamento_data_inc", nullable = false)
    private LocalDateTime dataInclusao;

    @Column(name = "plano_pagamento_pac_id", nullable = false)
    private Short pacoteId;

    @Column(name = "plano_pagamento_entrada")
    private Boolean entrada;

}
