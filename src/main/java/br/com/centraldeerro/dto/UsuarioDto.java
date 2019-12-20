package br.com.centraldeerro.dto;

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

}
