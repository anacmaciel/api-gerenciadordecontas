package com.catalisa.gerenciadordecontas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UsuarioSaidaDTO {
    private Long codigo;
    private String nomeUsuario;
    private LocalDate dataNascimento;
    private String email;

    public UsuarioSaidaDTO(UsuarioModel usuario) {
        this.codigo = usuario.getCodigo();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.email = usuario.getEmail();
    }
}
