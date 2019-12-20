package br.com.centraldeerro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogErroDto {

    @NotNull(message = "Data Hora do Erro Nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraErro;

    @NotNull
    @NotEmpty
    private String erro;

    @NotNull
    @NotBlank
    @Pattern(regexp = "debug|error|warning", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String tipoErro;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
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

    @JsonIgnore
    private String token;

    @JsonIgnore
    private String nomeUsuario;

    @JsonIgnore
    private String nickName;

}
