package br.com.centraldeerros.centraldeerro.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @Min(value = 1, message = "Quantidade de eventos menor que 1")
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataOcorrencia;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataEnvioOcorrencia;

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
        this.titulo = erro.getTitulo();
        this.detalhes = erro.getDetalhes();
        this.origem = erro.getOrigem();
        this.dataOcorrencia = erro.getDataOcorrencia();
        this.dataEnvioOcorrencia = erro.getDataEnvioOcorrencia();
        this.plataforma = erro.getPlataforma();
        this.versaoPlataforma = erro.getVersaoPlataforma();
    }

}
