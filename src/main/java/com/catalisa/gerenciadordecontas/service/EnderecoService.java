package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.EnderecoModel;
import com.catalisa.gerenciadordecontas.repository.EnderecoRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoModel> exibirtodos() {
        return enderecoRepository.findAll();
    }

    public Optional<EnderecoModel> buscarPorId(Long codigo) {
        if (!enderecoRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("este endereço não consta em nossa base de dados");
        }
        return enderecoRepository.findById(codigo);
    }

    public EnderecoModel cadastrar(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public EnderecoModel atualizar(Long codigo, EnderecoModel enderecoModel) {
        Optional<EnderecoModel> optionalEnderecoModel = enderecoRepository.findById(codigo);
        if (optionalEnderecoModel.isEmpty()) {
            throw new ObjectNotFoundException("este endereço não está em nossa base de dados");
        }
        EnderecoModel enderecoEncontrado = optionalEnderecoModel.get();
        String logradouroInformado = enderecoModel.getLogradouro();
        enderecoEncontrado.setLogradouro(logradouroInformado);
        String bairroInformado = enderecoModel.getBairro();
        enderecoEncontrado.setBairro(bairroInformado);
        return enderecoRepository.save(enderecoEncontrado);
    }

    public void deletar(Long codigo) {
        if (!enderecoRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("este endereço não foi encontrado em nossa base de dados");
        } else {
            enderecoRepository.deleteById(codigo);
        }
    }
}


