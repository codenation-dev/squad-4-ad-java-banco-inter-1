package br.com.centraldeerros.centraldeerro.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = LogErro.NOME_TABELA)
public class LogErro {
    public static final String NOME_TABELA = "log_erro";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data Hora do Erro Nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraErro;

    @NotNull(message = "Data Hora do Erro Nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraCriacao;

    @NotNull
    @NotEmpty
    private String erro;

    @NotNull
    private Long tipoErro;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    @NotEmpty
    private String classificacao;

    @NotNull
    @NotEmpty
    @NotBlank
    private String ip;
    
    private String plataformaOrigemErro;

    private String versaoPlataforma;

    @NotNull
    @NotEmpty
    private String sistemaOperacional;

    @NotNull
    @NotEmpty
    private String versaoSO;

    @NotNull
    private Boolean arquivado;

    @NotNull
    private Long idUsuario;

    @NotBlank
    @NotNull
    @NotEmpty
    private String token;

    


}
