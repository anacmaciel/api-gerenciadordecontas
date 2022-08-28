package com.catalisa.gerenciadordecontas;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.model.ContasAPagarModel;

import java.util.List;
import java.util.stream.Collectors;

public class ContasAPagarResposta {
    private Long id;
    private String nome;
    private double valor;
    private Status status;

    public ContasAPagarResposta(ContasAPagarModel contasAPagarModel) {
    this.id = contasAPagarModel.getId();
    this.nome = contasAPagarModel.getNome();
    this.valor = contasAPagarModel.getValor();
    this.status = contasAPagarModel.getStatus();
    }
    public static List<ContasAPagarResposta> converter(List<ContasAPagarModel> contas) {return contas.stream().map(ContasAPagarResposta::new).collect(Collectors.toList());
    }
}
