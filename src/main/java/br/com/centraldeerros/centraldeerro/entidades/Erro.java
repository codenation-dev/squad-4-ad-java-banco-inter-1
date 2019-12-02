package br.com.centraldeerros.centraldeerro.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column
    private LogErro logErro;


}
