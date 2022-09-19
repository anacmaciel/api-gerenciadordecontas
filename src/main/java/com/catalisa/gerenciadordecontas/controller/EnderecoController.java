package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.model.EnderecoModel;
import com.catalisa.gerenciadordecontas.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/enderecos")
public class EnderecoController {
@Autowired
    private EnderecoService enderecoService;
@GetMapping
public ResponseEntity<List<EnderecoModel>> exibirtodosEnderecos() {
    return ResponseEntity.ok(enderecoService.exibirtodos());
}

@GetMapping(path = "/{codigo}")
public ResponseEntity<EnderecoModel> exibirEnderecoEspecifico(@PathVariable Long codigo) {
    return ResponseEntity.ok(enderecoService.buscarPorId(codigo));
}

@PutMapping(path = "/{codigo}")
    public ResponseEntity<EnderecoModel> atualizarEndereco(@PathVariable Long codigo, @RequestBody @Valid EnderecoModel enderecoModel) {
    return ResponseEntity.ok(enderecoService.atualizar(codigo, enderecoModel));
}

@PostMapping
    public ResponseEntity<EnderecoModel> cadastrar(@RequestBody @Valid EnderecoModel enderecoModel) {
    EnderecoModel endereco = enderecoService.cadastrar(enderecoModel);
    return new ResponseEntity<>(endereco, HttpStatus.CREATED);
}

@DeleteMapping(path = "/{codigo}")

public void deletarEndereco(@PathVariable Long codigo) {
    enderecoService.deletar(codigo);
}
}
