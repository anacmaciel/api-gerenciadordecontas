package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ContasAPagarDTO {
private Long id;
private String nome;
private double valor;
private Status status;

    public ContasAPagarDTO(ContasAPagarModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.valor =model.getValor();
        this.status =model.getStatus();
    }

    public static List<ContasAPagarDTO> converter(List<ContasAPagarModel> contasAPagarModels) {
        List<ContasAPagarDTO> novaLista = new ArrayList<>();
        for (ContasAPagarModel model : contasAPagarModels) {
            ContasAPagarDTO novaContaDto = new ContasAPagarDTO(model);
            novaLista.add(novaContaDto);
        }
        return novaLista;
    }
}
