package com.catalisa.gerenciadordecontas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "estado")
public class EstadoModel implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long codigo;
private String uf;
private String nomeEstado;
@JsonIgnore
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<CidadeModel> cidades;
}
