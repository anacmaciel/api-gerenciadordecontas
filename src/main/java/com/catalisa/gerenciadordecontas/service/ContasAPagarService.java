package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import com.catalisa.gerenciadordecontas.model.ContasAPagarDTO;
import com.catalisa.gerenciadordecontas.model.ContasAPagarModel;
import com.catalisa.gerenciadordecontas.repository.ContasAPagarRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
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

    public List<ContasAPagarDTO> listarContas() {
        List<ContasAPagarModel> contasAPagarModels = contasAPagarRepository.findAll();
        return ContasAPagarDTO.converter(contasAPagarModels);
    }

    public Optional<ContasAPagarModel> buscarPorId(Long id) {
        if (!contasAPagarRepository.existsById(id)) {
            throw new ObjectNotFoundException("conta não localizada, não foi registrada ou já foi excluída");
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
        Status registrarStatus = Status.validarStatus(contasAPagarModel.getDataDeCadastro(), contasAPagarModel.getDataDeVencimento());
        contasAPagarModel.setStatus(registrarStatus);
        contasAPagarModel.setDataDePagamento(null);
        return contasAPagarRepository.save(contasAPagarModel);
    }

    public ContasAPagarModel alterar(ContasAPagarModel contaAPagarModel, Long id) {
        Optional<ContasAPagarModel> optionalContasAPagarModel = contasAPagarRepository.findById(id);
        if (optionalContasAPagarModel.isEmpty()) {
            throw new ObjectNotFoundException("esta conta não foi encontrada no sistema");
        }
        ContasAPagarModel contaEncontrada = optionalContasAPagarModel.get();
        if (contaEncontrada.getStatus() == Status.VENCIDA) {
            throw new ObjectNotFoundException("esta conta ja venceu");
        } else if (contaEncontrada.getStatus() == Status.PAGO) {
            throw new ObjectNotFoundException("esta conta ja foi paga");
        }
        if (contaEncontrada.getDataDePagamento() == null) {
            Status statusInformado = contaAPagarModel.getStatus();
            contaEncontrada.setStatus(statusInformado);
            contaEncontrada.setDataDePagamento(LocalDate.now(ZoneId.of("UTC-03:00")));
        }
        return contasAPagarRepository.save(contaEncontrada);
    }

    public void deletar(Long id) {

        if (!contasAPagarRepository.existsById(id)) {
            throw new ObjectNotFoundException("Objeto não encontrado, não existe ou já foi deletado.");
        }
        contasAPagarRepository.deleteById(id);
    }
}
