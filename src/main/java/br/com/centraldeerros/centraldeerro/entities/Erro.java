package br.com.centraldeerros.centraldeerro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Erro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String level;

    @Column
    private Long quantidadeDeEventos;


    @Column
    @NotNull
    @NotBlank
    private String titulo;

    @Column
    private String detalhes;

    @Column
    @NotNull
    @NotBlank
    private String origem;

    @Column
    @NotNull
    @NotBlank
    private String dataOcorrencia;

    @Column
    private String dataEnvioOcorrencia;

    // revisar esse atributo
    @Column
    private String token;

    @Column
    private String plataforma;

    @Column
    private String versaoPlataforma;

    public void update(Erro erro){
        this.level = erro.getLevel();
        this.quantidadeDeEventos = erro.getQuantidadeDeEventos();
    }

}
