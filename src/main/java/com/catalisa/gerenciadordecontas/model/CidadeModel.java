package com.catalisa.gerenciadordecontas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cidade")
@Data
public class CidadeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotBlank(message = "insira o nome da cidade")
    private String nomeCidade;
    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "codigo")
    private EstadoModel estado;
    @JsonIgnore
    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private List<EnderecoModel> enderecos;
}
