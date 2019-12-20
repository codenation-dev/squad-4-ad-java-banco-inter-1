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

    @NotNull(message = "Erro nulo")
    @NotEmpty(message = "Erro vazio")
    private String erro;

    @NotNull(message = "Tipo do erro nulo")
    @NotBlank(message = "Tipo do erro vazio")
    @Pattern(regexp = "debug|error|warning", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valores válidos: debug / error / warning")
    private String tipoErro;

    @NotNull(message = "Descrição nula")
    @NotEmpty(message = "Descrição vazia")
    private String descricao;

    @NotNull(message = "Endereço de ip nulo")
    @NotBlank(message = "Endereço de ip em branco")
    private String ip;

    private String plataformaOrigemErro;

    private String versaoPlataforma;

    @NotNull(message = "Sistema operacional nulo")
    @NotEmpty(message = "Sistema operacional vazio")
    private String sistemaOperacional;

    @NotNull(message = "Versão do SO nula")
    @NotEmpty(message = "Versão do SO vazia")
    private String versaoSO;

    @JsonIgnore
    private String token;

    @JsonIgnore
    private String nomeUsuario;

    @JsonIgnore
    private String nickName;

}
