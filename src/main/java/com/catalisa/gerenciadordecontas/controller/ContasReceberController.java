package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import com.catalisa.gerenciadordecontas.service.ContasReceberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/contasreceber")
public class ContasReceberController {
@Autowired
    private ContasReceberService contasReceberService;

@GetMapping(path = "/status/{status}")
public ResponseEntity<List<ContasReceberModel>> findByStatus(@PathVariable Status status) {
    return ResponseEntity.ok(contasReceberService.findByStatus(status));
}

@GetMapping
    public ResponseEntity<List<ContasReceberModel>> exibirtodasAscontas() {
    return ResponseEntity.ok(contasReceberService.listarContas());
}

@GetMapping(path = "/{codigo}")
public ResponseEntity<Optional<ContasReceberModel>> exibirContaReceberEspecifica(@PathVariable Long codigo) {
    return ResponseEntity.ok(contasReceberService.exibirContaEspecifica(codigo));
}

@PutMapping(path = "/{codigo}")
    public ResponseEntity<ContasReceberModel> alterarContaReceber(@PathVariable Long codigo, @RequestBody ContasReceberModel contasReceberModel) {
    return ResponseEntity.ok(contasReceberService.alterar(codigo, contasReceberModel));
}
@DeleteMapping(path = "/{codigo}")
    public void deletarContaReceber(@PathVariable Long codigo) {
    contasReceberService.deletar(codigo);
}
@PostMapping
    public ResponseEntity<ContasReceberModel> cadastrarContaReceber(@RequestBody ContasReceberModel contasReceberModel) {
    ContasReceberModel conta = contasReceberService.cadastrar(contasReceberModel);
    return new ResponseEntity<>(conta, HttpStatus.CREATED);
}
@GetMapping(path = "/tiporecebimento/{tipoRecebimento")
public ResponseEntity<List<ContasReceberModel>> findByTipoRecebimento(@PathVariable TipoRecebimento tipoRecebimento) {
    return ResponseEntity.ok(contasReceberService.findByTipoRecebimento(tipoRecebimento));
}
}
