package com.catalisa.gerenciadordecontas.controller;

import com.catalisa.gerenciadordecontas.model.UsuarioEntradaDTO;
import com.catalisa.gerenciadordecontas.model.UsuarioModel;
import com.catalisa.gerenciadordecontas.model.UsuarioSaidaDTO;
import com.catalisa.gerenciadordecontas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioSaidaDTO>> exibirTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.exibirTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> cadastrarUsuario(@RequestBody @Valid  UsuarioEntradaDTO entradaDTO) {
        UsuarioModel usuarioModel = usuarioService.cadastrar(entradaDTO.transformaParaObjeto());
        return new ResponseEntity<>(usuarioModel, HttpStatus.CREATED);
    }

@GetMapping(path = "/{codigo}")
public ResponseEntity<Optional<UsuarioModel>> buscarUsuarioPorId(@PathVariable Long codigo) {
return ResponseEntity.ok(usuarioService.buscarPorId(codigo));
}

@PutMapping(path = "/{codigo}")
public ResponseEntity<UsuarioModel> atualizarUsuario(@RequestBody UsuarioModel usuarioModel, @PathVariable Long codigo) {
return ResponseEntity.ok(usuarioService.atualizar(usuarioModel, codigo));
    }

    @DeleteMapping(path = "/{codigo}")
public void deletarUsuario(@PathVariable Long codigo) { usuarioService.deletar(codigo);
}
}
