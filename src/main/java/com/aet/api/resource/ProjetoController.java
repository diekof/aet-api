package com.aet.api.resource;

import com.aet.api.dto.ProjetoParteDTO;
import com.aet.api.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @GetMapping("/{id}/partes")
    public ResponseEntity<Map<String, List<? extends ProjetoParteDTO>>> buscarPartes(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPartesProjeto(id));
    }
}
