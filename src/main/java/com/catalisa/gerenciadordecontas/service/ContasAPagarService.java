package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import com.catalisa.gerenciadordecontas.model.ContasAPagarModel;
import com.catalisa.gerenciadordecontas.repository.ContasAPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ContasAPagarService {

    @Autowired
    private ContasAPagarRepository contasAPagarRepository;


    public List<ContasAPagarModel> buscarTodas() {
        return contasAPagarRepository.findAll();
    }

    public Optional<ContasAPagarModel> buscarPorId(Long id) {
        if (!contasAPagarRepository.existsById(id)) {
            throw new RuntimeException("conta não localizada, não foi registrada ou já foi excluída");
        }
        return contasAPagarRepository.findById(id);
    }

    public List<ContasAPagarModel> findByTipo(Tipo tipo) {
        return contasAPagarRepository.findByTipo(tipo);
    }

    public List<ContasAPagarModel> findByStatus(Status status) {
        return contasAPagarRepository.findByStatus(status);
    }

    public ContasAPagarModel cadastrar(ContasAPagarModel contasAPagarModel) {
        contasAPagarModel.setDataDeCadastro(LocalDate.now(ZoneId.of("UTC-03:00")));
        Status inserirDatas = Status.validarDatas(contasAPagarModel.getDataDeCadastro(), contasAPagarModel.getDataDeVencimento());
        contasAPagarModel.setStatus(inserirDatas);
        contasAPagarModel.setDataDePagamento(null);
        return contasAPagarRepository.save(contasAPagarModel);
    }

    public ContasAPagarModel alterar(ContasAPagarModel contaAPagarModel, Long id) {
        Optional<ContasAPagarModel> optionalContasAPagarModel = contasAPagarRepository.findById(id);
        if (optionalContasAPagarModel.isEmpty()) {
            throw new RuntimeException("esta conta não foi encontrada no sistema");
        }
        ContasAPagarModel contaEncontrada = optionalContasAPagarModel.get();
        if (contaEncontrada.getStatus() == Status.VENCIDA) {
            throw new RuntimeException("esta conta ja venceu");
        } else if (contaEncontrada.getStatus() == Status.PAGO) {
            throw new RuntimeException("esta conta ja foi paga");
        }
        Status statusInformado = contaAPagarModel.getStatus();
        contaEncontrada.setStatus(statusInformado);
        return contasAPagarRepository.save(contaEncontrada);
    }
        public void deletar (Long id){

            if (!contasAPagarRepository.existsById(id)) {
                throw new RuntimeException("Objeto não encontrado, não existe ou já foi deletado.");
            }
            contasAPagarRepository.deleteById(id);
        }
    }
