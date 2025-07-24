package com.aet.api.model;

import javax.persistence.*;

@Entity
@Table(name = "Combinacao_veicular")
public class CombinacaoVeicular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Combinacao_veicular_id")
    private Integer id;

    @Column(name = "Combinacao_veicular_desc", nullable = false, length = 100)
    private String descricao;

    // Getters e Setters (ou anotações do Lombok como @Data/@Getter/@Setter)
}
