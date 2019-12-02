package br.com.centraldeerros.centraldeerro.entidades;

import java.time.LocalDateTime;

public class LogErro {

    private Long id;

    private String titulo;

    private String detalhes;

    private String origem;

    private LocalDateTime dataOcorrencia;

    private LocalDateTime dataEnvioOcorrencia;

    // revisar esse atributo
    private String token;

    private String plataforma;

    private String versaoPlataforma;
}
