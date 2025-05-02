package com.aet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "UCLIENTE", columnNames = {"cliente_tipo", "cliente_documento"})
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(name = "cliente_tipo", nullable = false)
    private Short tipo;

    @Column(name = "cliente_documento", nullable = false, length = 40)
    private String documento;

    @Column(name = "cliente_grupo", nullable = false)
    private Short grupo;

    @Column(name = "cliente_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cliente_nome_razao", nullable = false, length = 200)
    private String nomeRazao;

    @Column(name = "cliente_ativo")
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Short getGrupo() {
        return grupo;
    }

    public void setGrupo(Short grupo) {
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeRazao() {
        return nomeRazao;
    }

    public void setNomeRazao(String nomeRazao) {
        this.nomeRazao = nomeRazao;
    }

}

