package com.catalisa.gerenciadordecontas.service;

import com.catalisa.gerenciadordecontas.model.ContasReceberModel;
import com.catalisa.gerenciadordecontas.repository.ContasReceberRepository;
import com.catalisa.gerenciadordecontas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContasReceberService {
    @Autowired
    private ContasReceberRepository contasReceberRepository;

    public List<ContasReceberModel> exibirContas() {
        return contasReceberRepository.findAll();
    }

    public Optional<ContasReceberModel> visualisarContaEspecifica(Long codigo) {
        if (!contasReceberRepository.existsById(codigo)) {
            throw new ObjectNotFoundException("esta conta não foi encontrada no sistema");
        }
        return contasReceberRepository.findById(codigo);
    }


}
