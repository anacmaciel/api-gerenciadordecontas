package com.catalisa.gerenciadordecontas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UsuarioSaidaDTO {
    private Long codigo;
    private String nomeUsuario;
    private LocalDate dataNascimento;
    private String email;
private List<EnderecoModel> enderecos;
private List<ContasReceberModel> contasReceber;
    public UsuarioSaidaDTO(UsuarioModel usuario) {
        this.codigo = usuario.getCodigo();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.dataNascimento = usuario.getDataNascimento();
        this.email = usuario.getEmail();
        this.enderecos = usuario.getEnderecos();
        this.contasReceber = usuario.getContasReceber();
    }
    public static List<UsuarioSaidaDTO> converter(List<UsuarioModel> usuarioModels) {
        return usuarioModels.stream().map(UsuarioSaidaDTO::new).collect(Collectors.toList());
    }
}
