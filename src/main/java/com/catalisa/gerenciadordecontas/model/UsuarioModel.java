package com.catalisa.gerenciadordecontas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String nomeUsuario;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    @CPF
    private String cpf;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EnderecoModel> enderecos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ContasReceberModel> contasReceber;

    public UsuarioModel(String nomeUsuario, String email, String cpf, LocalDate dataNascimento, List<EnderecoModel> enderecos, List<ContasReceberModel> contasReceber) {
        this.nomeUsuario = nomeUsuario;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
        this.contasReceber = contasReceber;
    }
}

