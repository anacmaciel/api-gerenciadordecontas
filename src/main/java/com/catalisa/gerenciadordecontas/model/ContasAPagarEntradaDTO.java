package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class ContasAPagarEntradaDTO {
    private String nome;
    private double valor;
    private Tipo tipo;
    private LocalDate dataDeVencimento;

    public ContasAPagarModel transformaParaObjeto() {
        return new ContasAPagarModel(nome, valor, tipo, dataDeVencimento);
    }
}
