package br.com.centraldeerro.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraErro;

    @NotNull(message = "Data Hora do Erro Nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataHoraCriacao;

    @Column
    private String erro;
    
    @Column
    private String tipoErro;

    @Column
    private String descricao;

    @Column
    private String ip;

    @Column
    private String plataformaOrigemErro;

    @Column
    private String versaoPlataforma;

    @Column
    private String sistemaOperacional;

    @Column
    private String versaoSO;

    @NotNull
    private Boolean arquivado;

    @Column
    private String nomeUsuario;

    @Column
    String nickName;

    @Column
    private String token;
}
