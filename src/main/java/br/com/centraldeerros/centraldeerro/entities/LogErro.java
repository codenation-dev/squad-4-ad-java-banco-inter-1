package br.com.centraldeerros.centraldeerro.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
public class LogErro {

    @Id
    private Long id;

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
    private LocalDateTime dataOcorrencia;

    @Column
    private LocalDateTime dataEnvioOcorrencia;

    // revisar esse atributo
    @Column
    private String token;

    @Column
    private String plataforma;

    @Column
    private String versaoPlataforma;
}
