package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.enums.RecebimentoAlugueis;
import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import com.catalisa.gerenciadordecontas.repository.ContasReceberRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContasReceberService {
    @Autowired
    private ContasReceberRepository contasReceberRepository;

    public List<ContasReceberModel> listarContas() {
        return contasReceberRepository.findAll();
    }

    public Optional<ContasReceberModel> exibirContaEspecifica(Long codigo) {
        if (!contasReceberRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("esta conta não foi encontrada no sistema");
        }
        return contasReceberRepository.findById(codigo);
    }

    public ContasReceberModel cadastrar(ContasReceberModel contasReceberModel) {
        Status inserirStatus = Status.validarDatas(contasReceberModel.getDataDeCadastro(), contasReceberModel.getDataDeVencimento());
        contasReceberModel.setStatus(inserirStatus);
        contasReceberModel.setDataRecebimento(null);
        boolean recebimentoEmDia =
                LocalDate.now().isBefore(contasReceberModel.getDataDeVencimento())
                        || LocalDate.now().equals(contasReceberModel.getDataDeVencimento());
        if (Boolean.FALSE.equals(recebimentoEmDia)) {
            contasReceberModel.setRecebimentoAlugueis(RecebimentoAlugueis.EM_ATRASO);
        } else if (Boolean.TRUE.equals(recebimentoEmDia)) {
            contasReceberModel.setRecebimentoAlugueis(RecebimentoAlugueis.EM_DIA);
        } else {
            contasReceberModel.setRecebimentoAlugueis(RecebimentoAlugueis.ADIANTADO);
        }
        return contasReceberRepository.save(contasReceberModel);
    }

    public ContasReceberModel alterar(Long codigo, ContasReceberModel contasReceberModel) {
        Optional<ContasReceberModel> optionalContasReceberModel = contasReceberRepository.findById(codigo);
        if (optionalContasReceberModel.isEmpty()) {
            throw new ObjectNotFoundException("esta conta não está cadastrada no sistema");
        }
        ContasReceberModel contaEncontrada = optionalContasReceberModel.get();
        if (contaEncontrada.getStatus() == Status.PAGO) {
            contaEncontrada.setDataRecebimento(LocalDateTime.now());
        }
        String recebimentoInformado = contasReceberModel.getRecebimento();
        contaEncontrada.setRecebimento(recebimentoInformado);
        LocalDate dataDeVencimentoInformada = contasReceberModel.getDataDeVencimento();
        contaEncontrada.setDataDeVencimento(dataDeVencimentoInformada);
        TipoRecebimento tipoRecebimentoInformado = contasReceberModel.getTipoRecebimento();
        contaEncontrada.setTipoRecebimento(tipoRecebimentoInformado);
        BigDecimal valorRecebimentoInformado = contasReceberModel.getValorRecebimento();
        contaEncontrada.setValorRecebimento(valorRecebimentoInformado);
        boolean recebimentoEmDia =
                LocalDate.now().isBefore(contasReceberModel.getDataDeVencimento())
                        || LocalDate.now().equals(contasReceberModel.getDataDeVencimento());
        if (Boolean.FALSE.equals(recebimentoEmDia)) {
            contaEncontrada.setRecebimentoAlugueis(RecebimentoAlugueis.EM_ATRASO);
        } else if (Boolean.TRUE.equals(recebimentoEmDia)) {
            contaEncontrada.setRecebimentoAlugueis(RecebimentoAlugueis.EM_DIA);
        } else {
            contaEncontrada.setRecebimentoAlugueis(RecebimentoAlugueis.ADIANTADO);
        }
        return contasReceberRepository.save(contaEncontrada);
    }

    public void deletar(Long codigo) {
        if (!contasReceberRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("não encontramos esta conta em nossa base de dados");
        }
        contasReceberRepository.deleteById(codigo);
    }
}
