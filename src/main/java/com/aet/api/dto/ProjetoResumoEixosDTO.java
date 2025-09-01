package com.aet.api.dto;

import java.math.BigDecimal;

public interface ProjetoResumoEixosDTO {
    Long getProjetoId();

    BigDecimal getQtdTipoeixoPeso();
    Integer getNiveis();

    Integer getQtdNivel1();
    Integer getQtdNivel2();
    Integer getQtdNivel3();
    Integer getQtdNivel4();

    BigDecimal getModeloVeiculoReducao1ic();
    BigDecimal getModeloVeiculoReducao2ic();
    BigDecimal getModeloVeiculoReducao3ic();
    BigDecimal getModeloVeiculoReducao4ic();
    BigDecimal getModeloVeiculoReducao5ic();
    BigDecimal getModeloVeiculoReducao6ic();
    BigDecimal getModeloVeiculoReducao7ic();
    BigDecimal getModeloVeiculoReducao8ic();
    BigDecimal getModeloVeiculoReducao9ic();
    BigDecimal getModeloVeiculoReducao10ic();
    BigDecimal getModeloVeiculoReducao11ic();
    BigDecimal getModeloVeiculoReducao12ic();
    BigDecimal getModeloVeiculoReducao13ic();
    BigDecimal getModeloVeiculoReducao14ic();
    BigDecimal getModeloVeiculoReducao15ic();
    BigDecimal getModeloVeiculoReducao16ic();
    BigDecimal getModeloVeiculoReducao17ic();
    BigDecimal getModeloVeiculoReducao18ic();
    BigDecimal getModeloVeiculoReducao19ic();
    BigDecimal getModeloVeiculoReducao20ic();

    Integer getQtdMarchaa();

    BigDecimal getCoeficienteAtrito();
    BigDecimal getRaio();
    BigDecimal getResistenciaRolamento();
    String getTipoPneu();
    BigDecimal getTorque();
    BigDecimal getReducaoMax();
    BigDecimal getReducaoEixoTras();
    Integer getRPM();
}