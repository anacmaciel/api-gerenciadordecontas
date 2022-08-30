package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "gerenciador_de_contas")
public class ContasAPagarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_de_cadastro")
    private LocalDate dataDeCadastro;
    @Column(name = "nome")
    private String nome;
    @Column(name = "valor")
    private double valor;
    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private Tipo tipo;
    @Column(name = "data_de_vencimento")
    private LocalDate dataDeVencimento;
    @Column(name = "data_de_pagamento")
    private LocalDate dataDePagamento;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public ContasAPagarModel(String nome, double valor, Tipo tipo, LocalDate dataDeVencimento) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        this.dataDeVencimento = dataDeVencimento;
    }
}
