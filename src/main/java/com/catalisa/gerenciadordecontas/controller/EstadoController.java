package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.model.EstadoModel;
import com.catalisa.gerenciadordecontas.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;
@GetMapping
    public ResponseEntity<List<EstadoModel>> exibirTodosEstados() {
        return ResponseEntity.ok(estadoService.exibirEstados());
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<EstadoModel> exibirEstadoPorId(@PathVariable long codigo) {
        return ResponseEntity.ok(estadoService.buscarEstadoPorId(codigo));
    }

    @PostMapping
    public ResponseEntity<EstadoModel> cadastrarEstado(@RequestBody EstadoModel estadoModel) {
        EstadoModel estado = estadoService.cadastrar(estadoModel);
        return new ResponseEntity<>(estado, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{codigo}")
    public ResponseEntity<EstadoModel> alterarEstado(@PathVariable Long codigo, @RequestBody @Valid  EstadoModel estadoModel) {
        return ResponseEntity.ok(estadoService.atualizar(codigo, estadoModel));
    }

    @DeleteMapping(path = "/{codigo}")
    public void deletarEstado(@PathVariable Long codigo) {
        estadoService.deletar(codigo);
    }
}
