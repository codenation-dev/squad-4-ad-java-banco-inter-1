package br.com.centraldeerros.centraldeerro.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class Erro {

    @Id
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String level;

    @Column
    private Long quantidadeDeEventos;

    @OneToOne
    private LogErro logErro;


}
