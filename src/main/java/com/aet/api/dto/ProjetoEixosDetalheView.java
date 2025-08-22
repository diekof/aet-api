package com.aet.api.dto;


import java.math.BigDecimal;

public interface ProjetoEixosDetalheView {
    Long getProjetoId();
    String getLetra();
    Integer getTipoEixoRodas();
    String getTipoEixoTandem();
    BigDecimal getTipoEixoDistancia();
    Integer getTipoEixoTipo();
    String getEixo();
    BigDecimal getTipoEixoL();
    Integer getFatorL();
    BigDecimal getTipoEixoPeso();
    Integer getNivel();
}