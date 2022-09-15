package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.UsuarioModel;
import com.catalisa.gerenciadordecontas.model.UsuarioSaidaDTO;
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

    public List<UsuarioSaidaDTO> exibirTodos() {
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
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

    public UsuarioModel atualizar(UsuarioModel usuario, Long codigo) {
        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository.findById(codigo);
        if (optionalUsuarioModel.isEmpty()) {
            throw new ObjectNotFoundException("este usuário não existe");
        }
        UsuarioModel usuarioEncontrado = optionalUsuarioModel.get();
        String nomeInformado = usuario.getNomeUsuario();
        usuarioEncontrado.setNomeUsuario(nomeInformado);
        String emailInformado = usuario.getEmail();
        usuarioEncontrado.setEmail(emailInformado);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long codigo) {
        if (!usuarioRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("Usuário não encontrado, não existe ou já foi excluído");
        } else {
            usuarioRepository.deleteById(codigo);
        }
    }
}

