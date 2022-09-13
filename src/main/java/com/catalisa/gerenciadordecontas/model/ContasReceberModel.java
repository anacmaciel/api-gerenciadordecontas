package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.RecebimentoAlugueis;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contas_receber")
public class ContasReceberModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String recebimento;
    @Enumerated(EnumType.STRING)
    private TipoRecebimento tipoRecebimento;
    @Enumerated(EnumType.STRING)
    private RecebimentoAlugueis recebimentoAlugueis;
    private LocalDate dataDeVencimento;
    private BigDecimal valorRecebimento;

    private String Status;


@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "codigo")
    private UsuarioModel usuario;



}

