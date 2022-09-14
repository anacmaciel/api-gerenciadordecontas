package com.catalisa.gerenciadordecontas.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UsuarioEntradaDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Length(min = 5, max = 30, message = "mínimo de 5 caractéres e máximo de 30")
    private String nomeUsuario;
    @NotBlank(message = "insira um email")
    @Email
    private String email;
    @NotBlank(message = "insira um cpf")
    @CPF
    private String cpf;
    @NotBlank(message = "insira sua data de nascimento")
    private LocalDate dataNascimento;

    public UsuarioModel transformaParaObjeto() {
        return new UsuarioModel(nomeUsuario, email, cpf, dataNascimento);
    }
}
