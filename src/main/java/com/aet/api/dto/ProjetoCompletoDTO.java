package com.aet.api.dto;


import java.util.List;

public class ProjetoCompletoDTO {

    private ProjetoResumoEixosDTO resumo;
    private List<ProjetoEixosDetalheView> detalhes;

    public ProjetoCompletoDTO(ProjetoResumoEixosDTO resumo, List<ProjetoEixosDetalheView> detalhes) {
        this.resumo = resumo;
        this.detalhes = detalhes;
    }

    public ProjetoResumoEixosDTO getResumo() {
        return resumo;
    }

    public void setResumo(ProjetoResumoEixosDTO resumo) {
        this.resumo = resumo;
    }

    public List<ProjetoEixosDetalheView> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<ProjetoEixosDetalheView> detalhes) {
        this.detalhes = detalhes;
    }
}
