package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.Tipo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "gerenciador_de_conntas")
public class ContasAPagarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(length = 50, nullable = false)
    private String nome;
@Column(length = 50, nullable = false)
    private double valor;
@Column(nullable = false)
private Tipo tipo;
@Column(nullable = false)
    private LocalDate dataDeVencimento;
@Column()
    private LocalDateTime dataDePagamento;
@Column(nullable = false)
    private Status status;
}
