package br.com.centraldeerro.models.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDto {

    private String usuario;
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(usuario, senha);
    }
}
