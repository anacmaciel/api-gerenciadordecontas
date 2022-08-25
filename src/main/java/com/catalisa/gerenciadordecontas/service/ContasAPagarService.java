package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import com.catalisa.gerenciadordecontas.model.ContasAPagarModel;
import com.catalisa.gerenciadordecontas.repository.ContasAPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContasAPagarService {

@Autowired
    private ContasAPagarRepository contasAPagarRepository;

public List<ContasAPagarModel> buscarTodas() {
    return contasAPagarRepository.findAll();
}

public ContasAPagarModel buscarPorId(Long id) {
    Optional<ContasAPagarModel> contasAPagarModelOptional = contasAPagarRepository.findById(id);
    if (contasAPagarModelOptional.isPresent()) {
        return contasAPagarModelOptional.get();
    } else {
        throw new ChangeSetPersister.NotFoundException("Objeto nnão encontrado");
    }
}

    public List<ContasAPagarModel> findByTipo(Tipo tipo) {
    return contasAPagarRepository.findByTipo(tipo);
    }

    public List<ContasAPagarModel> findByStatus(Status status) {
    return contasAPagarRepository.findByStatus(status);
    }


    public ContasAPagarModel cadastrar(ContasAPagarModel contasAPagarModel) {
    contasAPagarModel.getId();
    contasAPagarModel.getNome();
    contasAPagarModel.getValor();
    contasAPagarModel.getTipo();
    contasAPagarModel.getDataDeVencimento();
    contasAPagarModel.setDataDePagamento(LocalDateTime.now(contasAPagarModel.getDataDeVencimento()));
    return contasAPagarRepository.save(contasAPagarModel);
}

public ResponseEntity<ContasAPagarModel> alterar(ContasAPagarModel contasAPagarModel) {
    ontasAPagarModel.getId();
    contasAPagarModel.getNome();
    contasAPagarModel.getValor();
    contasAPagarModel.getTipo();
    contasAPagarModel.getDataDeVencimento();
    contasAPagarModel.setDataDePagamento(LocalDateTime.now(contasAPagarModel.getDataDeVencimento()));
    return contasAPagarRepository.save(contasAPagarModel);
}



public void deletar(Long id) {
    contasAPagarRepository.deleteById(id);
}
}
