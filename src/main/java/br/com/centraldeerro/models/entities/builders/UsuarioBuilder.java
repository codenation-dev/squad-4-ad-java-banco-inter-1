package br.com.centraldeerro.models.entities.builders;

import br.com.centraldeerro.Utils.GeradorNumeroAleatorio;
import br.com.centraldeerro.models.entities.Usuario;
import br.com.centraldeerro.models.entities.dto.UsuarioDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UsuarioBuilder {
    public static Usuario CriaUsuarioAllParameters(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        LocalDateTime localDate = LocalDateTime.now();

        return usuario.builder()
                .ativo(true)
                .admin(true)
                .nome(usuarioDto.getNome())
                .password(new BCryptPasswordEncoder().encode(usuarioDto.getSenha()))
                .username(usuarioDto.getUserName())
                .dataHoraCriacao(Date.from(localDate.atZone(ZoneId.of("America/Sao_Paulo")).toInstant()))
                .email(usuarioDto.getEmail())
                .codRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico())
                .build();
    }
}
