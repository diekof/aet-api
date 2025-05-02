package com.aet.api.service;

import com.aet.api.model.TrechoRodovia;
import com.aet.api.repositories.TrechoRodoviaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrechoRodoviaService {
    @Autowired
    private TrechoRodoviaRepository trechoRodoviaRepository;

    public TrechoRodovia salvar(TrechoRodovia rodovia) {
        return trechoRodoviaRepository.save(rodovia);
    }

    public List<TrechoRodovia> listarTodos() {
        return trechoRodoviaRepository.findAll();
    }

    public List<TrechoRodovia> listarPorTrecho(Long trechoId) {
        return trechoRodoviaRepository.findByTrechoId(trechoId);
    }

    public Optional<TrechoRodovia> buscarPorId(Integer id) {
        return trechoRodoviaRepository.findById(id);
    }

    public void deletar(Integer id) {
        trechoRodoviaRepository.deleteById(id);
    }
}

