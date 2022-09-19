package com.catalisa.gerenciadordecontas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
public class EnderecoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotBlank(message = "insira o logradouro.")
    private String logradouro;
    @NotBlank(message = "insira o nome do bairro")
    private String bairro;
    @NotBlank(message = "cep é obrigatório")
    @Column(length = 8)
    private String cep;
    private String pontoReferencia;
    @NotNull(message = "insira um usuario")
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "codigo")
    private UsuarioModel usuario;
    @NotNull(message = "cidade é obrigatória")
    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "codigo")
    private CidadeModel cidade;
}
