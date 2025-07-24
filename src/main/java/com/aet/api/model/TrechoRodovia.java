package com.aet.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TRECHO_RODOVIA")
public class TrechoRodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RODOVIA_ID")
    private Integer id;

    @Column(name = "TRECHO_ID", nullable = false)
    private Integer trechoId;

    @Column(name = "BR", nullable = false, length = 3)
    private String br;

    @Column(name = "ESTADO_ID", nullable = false, length = 2)
    private String estadoId;

    @Column(name = "KM_INICIAL", nullable = false, precision = 12, scale = 2)
    private BigDecimal kmInicial;

    @Column(name = "KM_FINAL", nullable = false, precision = 12, scale = 2)
    private BigDecimal kmFinal;

    @Column(name = "TRECHO_INICIAL", length = 100)
    private String trechoInicial;

    @Column(name = "TRECHO_FINAL", length = 100)
    private String trechoFinal;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Integer trechoId) {
        this.trechoId = trechoId;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }

    public BigDecimal getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(BigDecimal kmInicial) {
        this.kmInicial = kmInicial;
    }

    public BigDecimal getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(BigDecimal kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getTrechoInicial() {
        return trechoInicial;
    }

    public void setTrechoInicial(String trechoInicial) {
        this.trechoInicial = trechoInicial;
    }

    public String getTrechoFinal() {
        return trechoFinal;
    }

    public void setTrechoFinal(String trechoFinal) {
        this.trechoFinal = trechoFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
