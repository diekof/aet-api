package com.aet.api.model;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "trecho")
public class Trecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRECHO_ID")
    private Long id;

    @Column(name = "TRECHO_DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "TRECHO_SITUACAO", nullable = false, length = 1)
    private String situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
