package br.com.centraldeerros.centraldeerro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogErroDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraErro;

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
    private Long idUsuario;

    @NotNull
    @NotEmpty
    @NotBlank
    private String token;

}
