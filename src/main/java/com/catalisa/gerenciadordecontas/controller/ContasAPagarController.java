package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import com.catalisa.gerenciadordecontas.model.ContasAPagarDTO;
import com.catalisa.gerenciadordecontas.model.ContasAPagarEntradaDTO;
import com.catalisa.gerenciadordecontas.model.ContasAPagarModel;
import com.catalisa.gerenciadordecontas.service.ContasAPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContasAPagarController {

    @Autowired
    private ContasAPagarService contasAPagarService;

    @GetMapping(path = "/contas")
    public ResponseEntity<List<ContasAPagarModel>> buscartodasContas() {

        return ResponseEntity.ok(contasAPagarService.buscarTodas());
    }


    @GetMapping(path = "/contas/lista")
    public ResponseEntity<List<ContasAPagarDTO>> listarTodasContas() {
        return ResponseEntity.ok(contasAPagarService.listarContas());
    }

    @GetMapping(path = "contas/{id}")
    public ResponseEntity<Optional<ContasAPagarModel>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contasAPagarService.buscarPorId(id));
    }


    @GetMapping(path = "/contas/tipo/{tipo}")
    public ResponseEntity<List<ContasAPagarModel>> findByTipo(@PathVariable Tipo tipo) {
        return ResponseEntity.ok(contasAPagarService.findByTipo(tipo));
    }

    @GetMapping(path = "contas/status/{status}")
    public ResponseEntity<List<ContasAPagarModel>> findByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(contasAPagarService.findByStatus(status));
    }

    @PostMapping(path = "/contas")
    public ResponseEntity<ContasAPagarModel> cadastrarConta(@RequestBody ContasAPagarEntradaDTO contasAPagarEntradaDTO) {

        ContasAPagarModel contasAPagar = contasAPagarService.cadastrar(contasAPagarEntradaDTO.transformaParaObjeto());
        return new ResponseEntity<>(contasAPagar, HttpStatus.CREATED);
    }

    @PutMapping(path = "contas/{id}")
    public ResponseEntity<ContasAPagarModel> alterarConta(@RequestBody ContasAPagarModel contasAPagarModel, @PathVariable Long id) {
        return ResponseEntity.ok(contasAPagarService.alterar(contasAPagarModel, id));
    }

    @DeleteMapping(path = "/contas/{id}")
    public void deletarConta(@PathVariable Long id) {
        contasAPagarService.deletar(id);
    }

}
