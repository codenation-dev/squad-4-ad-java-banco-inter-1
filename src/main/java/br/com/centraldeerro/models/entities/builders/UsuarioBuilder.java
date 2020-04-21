package br.com.centraldeerro.models.entities.builders;

import br.com.centraldeerro.Utils.GeradorNumeroAleatorio;
import br.com.centraldeerro.models.entities.Usuario;
import br.com.centraldeerro.models.entities.dto.UsuarioDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UsuarioBuilder {
    public static Usuario CriaUsuario(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        LocalDateTime localDate = LocalDateTime.now();

        usuario.setAtivo(true);
        usuario.setAdmin(true);
        usuario.setNome(usuarioDto.getNome());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDto.getSenha()));
        usuario.setUsername(usuarioDto.getUserName());
        usuario.setDataHoraCriacao(Date.from(localDate.atZone(ZoneId.of("America/Sao_Paulo")).toInstant()));
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setCodRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico());

        /*usuarioDto.setId(usuario.getId());
        usuarioDto.setSenha("");
        return usuario;*/

        return usuario;
    }
}
