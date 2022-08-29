package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Tipo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContasAPagarEntradaDTO {
    private String nome;
    private double valor;
    private Tipo tipo;
    private LocalDate dataDeVencimento;
}
