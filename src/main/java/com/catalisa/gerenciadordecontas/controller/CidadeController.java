package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.model.CidadeModel;
import com.catalisa.gerenciadordecontas.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<CidadeModel>> listarcidades() {
        return ResponseEntity.ok(cidadeService.exibirtodas());
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<CidadeModel> buscarCidadePorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(cidadeService.buscarPorId(codigo));
    }

    @PostMapping
    public ResponseEntity<CidadeModel> cadastrarCidade(@RequestBody @Valid CidadeModel cidadeModel) {
        CidadeModel cidade = cidadeService.cadastrar(cidadeModel);
        return new ResponseEntity<>(cidade, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{codigo}/")
    public ResponseEntity<CidadeModel> atualizarCidade(@PathVariable Long codigo,@RequestBody @PathVariable CidadeModel cidadeModel) {
        return ResponseEntity.ok(cidadeService.atualizar(codigo, cidadeModel));
    }

    @DeleteMapping(path = "/{codigo}")
    public void deletarCidade(@PathVariable Long codigo) {
        cidadeService.deletar(codigo);
    }
}
