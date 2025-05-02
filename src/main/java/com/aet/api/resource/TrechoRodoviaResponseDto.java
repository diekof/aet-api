package com.aet.api.resource;

import lombok.Builder;

@Builder
public class TrechoRodoviaResponseDto {

    private String BR;
    private String UF;
    private String KM_INICIAL;
    private String KM_FINAL;
    private String TRECHO_INICIO;
    private String TRECHO_FINAL;
    private Integer ID_SEGMENTO;

    public String getBR() {
        return BR;
    }

    public void setBR(String BR) {
        this.BR = BR;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getKM_INICIAL() {
        return KM_INICIAL;
    }

    public void setKM_INICIAL(String KM_INICIAL) {
        this.KM_INICIAL = KM_INICIAL;
    }

    public String getKM_FINAL() {
        return KM_FINAL;
    }

    public void setKM_FINAL(String KM_FINAL) {
        this.KM_FINAL = KM_FINAL;
    }

    public String getTRECHO_INICIO() {
        return TRECHO_INICIO;
    }

    public void setTRECHO_INICIO(String TRECHO_INICIO) {
        this.TRECHO_INICIO = TRECHO_INICIO;
    }

    public String getTRECHO_FINAL() {
        return TRECHO_FINAL;
    }

    public void setTRECHO_FINAL(String TRECHO_FINAL) {
        this.TRECHO_FINAL = TRECHO_FINAL;
    }

    public Integer getID_SEGMENTO() {
        return ID_SEGMENTO;
    }

    public void setID_SEGMENTO(Integer ID_SEGMENTO) {
        this.ID_SEGMENTO = ID_SEGMENTO;
    }

}
