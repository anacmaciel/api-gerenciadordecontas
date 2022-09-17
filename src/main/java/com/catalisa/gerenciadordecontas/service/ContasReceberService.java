package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.enums.RecebimentoAlugueis;
import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import com.catalisa.gerenciadordecontas.factory.AlugueisFactory;
import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import com.catalisa.gerenciadordecontas.repository.ContasReceberRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ContasReceberService {
    @Autowired
    private ContasReceberRepository contasReceberRepository;

    public List<ContasReceberModel> listarContas() {
        return contasReceberRepository.findAll();
    }

    public ContasReceberModel exibirContaEspecifica(Long codigo) {
        Optional<ContasReceberModel> optionalContasReceberModel = contasReceberRepository.findById(codigo);
        if (optionalContasReceberModel.isEmpty()) {
            throw new ObjectNotFoundException("esta conta não foi encontrada no sistema");
        }
        return optionalContasReceberModel.get();
    }


    public ContasReceberModel cadastrar(AlugueisFactory alugueisFactory, ContasReceberModel contasReceberModel) {
        contasReceberModel.setDataDeCadastro(LocalDate.now(ZoneId.of("UTC-03:00")));
        Status RegistrarStatus = Status.validarStatus(contasReceberModel.getDataDeCadastro(), contasReceberModel.getDataDeVencimento());
        contasReceberModel.setStatus(RegistrarStatus);
        contasReceberModel.setDataRecebimento(null);
        if (contasReceberModel.getTipoRecebimento().equals(TipoRecebimento.ALUGUEIS)) {
            RecebimentoAlugueis validarStatusDeRecebimentoAluguel = RecebimentoAlugueis.validarRecebimentoAluguel(contasReceberModel.getDataDeVencimento());
            contasReceberModel.setRecebimentoAlugueis(validarStatusDeRecebimentoAluguel);
            BigDecimal resultadoValorFinal = alugueisFactory.iCalculoAlugueis(contasReceberModel).calculo(contasReceberModel.getValorRecebimento());
            contasReceberModel.setValorFinal(resultadoValorFinal);
        }

        return contasReceberRepository.save(contasReceberModel);
    }

    public ContasReceberModel alterar(Long codigo, ContasReceberModel contasReceberModel) {
        Optional<ContasReceberModel> optionalContasReceberModel = contasReceberRepository.findById(codigo);
        if (optionalContasReceberModel.isEmpty()) {
            throw new ObjectNotFoundException("esta conta não foi encontrada no sistema");
        }
        ContasReceberModel contaEncontrada = optionalContasReceberModel.get();
        if (contaEncontrada.getStatus() == Status.VENCIDA) {
            throw new ObjectNotFoundException("esta conta ja venceu");
        } else if (contaEncontrada.getStatus() == Status.PAGO) {
            throw new ObjectNotFoundException("esta conta ja foi paga");
        }
        if (contaEncontrada.getDataRecebimento() == null) {
            Status statusInformado = contasReceberModel.getStatus();
            contaEncontrada.setStatus(statusInformado);
            contaEncontrada.setDataRecebimento(LocalDate.now(ZoneId.of("UTC-03:00")));
        }
        return contasReceberRepository.save(contaEncontrada);
    }

    public void deletar(Long codigo) {
        Optional<ContasReceberModel> optionalContasReceberModel = contasReceberRepository.findById(codigo);
        if (optionalContasReceberModel.isEmpty()) {
            throw new ObjectNotFoundException("não encontramos esta conta em nossa base de dados");
        }
        contasReceberRepository.deleteById(codigo);
    }

    public List<ContasReceberModel> findByStatus(Status status) {
        return contasReceberRepository.findByStatus(status);
    }

    public List<ContasReceberModel> findByTipoRecebimento(TipoRecebimento tipoRecebimento) {
        return contasReceberRepository.findByTipoRecebimento(tipoRecebimento);
    }

    public List<ContasReceberModel> findByDataDeVencimento(LocalDate dataDeVencimento) {
        return contasReceberRepository.findByDataDeVencimento(dataDeVencimento);
    }
}
