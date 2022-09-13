package com.catalisa.gerenciadordecontas.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioEntradaDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Length(min = 5, max = 30, message = "mínimo de 5 caractéres e máximo de 30")
    private String nomeUsuario;
    @Email
    private String email;
    @CPF
    private String cpf;
    private LocalDate dataNascimento;
private List<EnderecoModel> enderecos;
private List<ContasReceberModel> contasReceber;

    public UsuarioModel transformaParaObjeto() {
        return new UsuarioModel(nomeUsuario, email, cpf, dataNascimento, enderecos, contasReceber);
    }
}
