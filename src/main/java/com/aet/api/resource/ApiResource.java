package com.aet.api.resource;

import com.aet.api.model.Trecho;
import com.aet.api.model.TrechoRodovia;
import com.aet.api.service.ClienteService;
import com.aet.api.model.Cliente;
import com.aet.api.service.TrechoRodoviaService;
import com.aet.api.service.TrechoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiResource {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TrechoService trechoService;
    @Autowired
    private TrechoRodoviaService trechoRodoviaService;

    @GetMapping("/prc_trecho_engineer")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/prc_trecho_segments")
    public ResponseEntity<List<Trecho>> listarTrechos() {
        List<Trecho> trechos = trechoService.listarTodos();
        return ResponseEntity.ok(trechos);
    }

    @GetMapping("/prc_trechos/{id}")
    public ResponseEntity<List<TrechoRodoviaResponseDto>> buscarPorId(@PathVariable Long id) {
        List<TrechoRodovia> trechoRodovia = trechoRodoviaService.listarPorTrecho(id);
        List<TrechoRodoviaResponseDto> dtos = TrechoRodoviaMapper.toDtoList(trechoRodovia);
        return ResponseEntity.ok(dtos);
    }

}
