package br.com.centraldeerros.centraldeerro.dto;

import br.com.centraldeerros.centraldeerro.entities.Usuario;
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
    @Min(value = 1, message = "Inserir valor entre 1 e 3")
    @Max(value = 3, message = "Inserir valor entre 1 e 3")
    private Long tipoErro;

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
