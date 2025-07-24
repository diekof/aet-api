package com.aet.api.service;

import com.aet.api.dto.ProjetoParteDTO;
import com.aet.api.repositories.ProjetoCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoCustomRepository repository;

    public Map<String, List<? extends ProjetoParteDTO>> listarPartesProjeto(Long projetoId) {
        Map<String, List<? extends ProjetoParteDTO>> resultado = new LinkedHashMap<>();

        resultado.put("Unidade1", repository.findUnidade1(projetoId));
        resultado.put("Unidade2", repository.findUnidade2(projetoId));
        resultado.put("Rodizio", repository.findRodizio(projetoId));

        return resultado;
    }
}
