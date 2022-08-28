package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Status;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContasAPagarDTO {
    private long id;
    private String nome;
    private double valor;
    private Status status;
    private LocalDate dataDeVencimento;
}
