package br.com.centraldeerros.centraldeerro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @NotNull(message = "Level do erro nulo")
    @NotBlank(message = "Level do erro em branco")
    private String level;

    @Column
    @Length(min = 1, message = "Quantidade de eventos menor que 1")
    private Long quantidadeDeEventos;


    @Column
    @NotNull(message = "Titulo não informado")
    @NotBlank(message = "Titulo em branco")
    private String titulo;

    @Column
    private String detalhes;

    @Column
    @NotNull
    @NotBlank
    private String origem;

    @Column
    @NotNull(message = "Data da ocorrência do erro não especificada")
    @NotBlank(message = "Data da ocorrência do erro em branco")
    
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
