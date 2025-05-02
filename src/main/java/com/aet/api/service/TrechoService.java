package com.aet.api.service;

import com.aet.api.model.Trecho;
import com.aet.api.repositories.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrechoService {
    @Autowired
    private TrechoRepository trechoRepository;

    public Trecho salvar(Trecho trecho) {
        return trechoRepository.save(trecho);
    }

    public List<Trecho> listarTodos() {
        return trechoRepository.findAll();
    }

    public Optional<Trecho> buscarPorId(Long id) {
        return trechoRepository.findById(id);
    }

    public void deletar(Long id) {
        trechoRepository.deleteById(id);
    }
}
