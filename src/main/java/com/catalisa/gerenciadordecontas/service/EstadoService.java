package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.EstadoModel;
import com.catalisa.gerenciadordecontas.repository.EstadoRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public List<EstadoModel> exibirEstados() {
        return estadoRepository.findAll();
    }

    public EstadoModel buscarEstadoPorId(Long codigo) {
        Optional<EstadoModel> optionalEstadoModel = estadoRepository.findById(codigo);
        if (optionalEstadoModel.isEmpty()) {
            throw new ObjectNotFoundException("Este estado não foi encontrado nesta base de dados");
        }
        return optionalEstadoModel.get();
    }

    public EstadoModel cadastrar(EstadoModel estadoModel) {
        return estadoRepository.save(estadoModel);
    }


    public EstadoModel atualizar(Long codigo, EstadoModel estadoModel) {
        Optional<EstadoModel> optionalEstadoModel = estadoRepository.findById(codigo);

        if (optionalEstadoModel.isEmpty()) {
            throw new ObjectNotFoundException("este estado não está na base de dados");
        }
        EstadoModel estadoEncontrado = optionalEstadoModel.get();
        String ufInformada = estadoModel.getUf();
        estadoEncontrado.setUf(ufInformada);
        String nomeEstadoInformado = estadoModel.getNomeEstado();
        estadoEncontrado.setNomeEstado(nomeEstadoInformado);

        return estadoRepository.save(estadoEncontrado);
    }

    public void deletar(Long codigo) {
        Optional<EstadoModel> optionalEstadoModel = estadoRepository.findById(codigo);
        if (optionalEstadoModel.isEmpty()) {
            throw new ObjectNotFoundException("este estado não está em nossa base de dados");
        } else {
            estadoRepository.deleteById(codigo);
        }
    }
}
