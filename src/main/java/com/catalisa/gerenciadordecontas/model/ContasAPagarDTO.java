package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Status;
import lombok.Getter;

@Getter
public class ContasAPagarDTO {
private Long id;
private String nome;
private double valor;
private Status status;

    public ContasAPagarDTO(Long id, String nome, double valor, Status status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }
}
