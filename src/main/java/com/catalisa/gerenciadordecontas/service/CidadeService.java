package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.CidadeModel;
import com.catalisa.gerenciadordecontas.model.EstadoModel;
import com.catalisa.gerenciadordecontas.repository.CidadeRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<CidadeModel> exibirtodas() {
        return cidadeRepository.findAll();
    }

    public CidadeModel buscarPorId(Long codigo) {
        Optional<CidadeModel> optionalCidadeModel = cidadeRepository.findById(codigo);
        if (optionalCidadeModel.isEmpty()) {
            throw new ObjectNotFoundException("esta cidade não consta em nossa base de dados");
        }
        return optionalCidadeModel.get();
    }

    public CidadeModel cadastrar(CidadeModel cidadeModel) {
        return cidadeRepository.save(cidadeModel);
    }

    public CidadeModel atualizar(Long codigo, CidadeModel cidadeModel) {
        Optional<CidadeModel> optionalCidadeModel = cidadeRepository.findById(codigo);
        if (optionalCidadeModel.isEmpty()) {
            throw new ObjectNotFoundException("esta cidade não está em nossa base de dados");
        }
        CidadeModel cidadeEncontrada = optionalCidadeModel.get();
        String nomeCidadeInformado = cidadeModel.getNomeCidade();
        cidadeEncontrada.setNomeCidade(nomeCidadeInformado);
        EstadoModel estadoInformado = cidadeModel.getEstado();
        cidadeEncontrada.setEstado(estadoInformado);
        return cidadeRepository.save(cidadeEncontrada);
    }

    public void deletar(Long codigo) {
        if (!cidadeRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("esta cidade não foi encontrada em nossa base de dados");
        } else {
            cidadeRepository.deleteById(codigo);
        }
    }
}
