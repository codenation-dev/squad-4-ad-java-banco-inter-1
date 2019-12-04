package br.com.centraldeerros.centraldeerro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
