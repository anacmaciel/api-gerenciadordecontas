package com.catalisa.gerenciadordecontas.model;

import com.catalisa.gerenciadordecontas.enums.RecebimentoAlugueis;
import com.catalisa.gerenciadordecontas.enums.Status;
import com.catalisa.gerenciadordecontas.enums.TipoRecebimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "campo vasio, insira o que irá receber")
    private String recebimento;
    @NotBlank(message = "insira o tipo de recebimento")
    @Enumerated(EnumType.STRING)
    private TipoRecebimento tipoRecebimento;
    @Enumerated(EnumType.STRING)
    private RecebimentoAlugueis recebimentoAlugueis;
    @NotNull(message = "data de vencimento é obrigatória")
    private LocalDate dataDeVencimento;
    @NotNull(message = "valor é obrigatório")
    private BigDecimal valorRecebimento;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dataRecebimento;
    private LocalDate dataDeCadastro;
    private BigDecimal valorFinal;

    @NotNull(message = "insira um usuario")
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "codigo")
    private UsuarioModel usuario;


}

