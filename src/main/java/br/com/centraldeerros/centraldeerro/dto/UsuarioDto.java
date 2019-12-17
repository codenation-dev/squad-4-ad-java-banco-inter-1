package br.com.centraldeerros.centraldeerro.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String userName;
    @NotNull
    private String senha;
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotNull
    private boolean administrador;
}
