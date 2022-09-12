package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.UsuarioModel;
import com.catalisa.gerenciadordecontas.model.UsuarioSaidaDTO;
import com.catalisa.gerenciadordecontas.repository.EnderecoRepository;
import com.catalisa.gerenciadordecontas.repository.UsuarioRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
@Autowired
    private UsuarioRepository usuarioRepository;
@Autowired
    private EnderecoRepository enderecoRepository;

public List<UsuarioSaidaDTO> exibirTodos() {List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
    return UsuarioSaidaDTO.converter(usuarioModels);
}

public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
    return usuarioRepository.save(usuarioModel);
}

public Optional<UsuarioModel> buscarPorId(Long codigo) {
    if (!usuarioRepository.existsById(codigo)) {
        throw new ObjectNotFoundException("usuário não encontrado, não foi cadastrado ou já foi excluído");
    }
    return usuarioRepository.findById(codigo);
    }
}

