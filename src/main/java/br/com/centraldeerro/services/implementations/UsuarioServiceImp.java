package br.com.centraldeerro.services.implementations;

import br.com.centraldeerro.Utils.GeradorNumeroAleatorio;
import br.com.centraldeerro.dto.UsuarioDto;
import br.com.centraldeerro.entities.Usuario;
import br.com.centraldeerro.exceptions.ResourceNotFoundException;
import br.com.centraldeerro.exceptions.UsuarioException;
import br.com.centraldeerro.repositories.UsuarioRepository;
import br.com.centraldeerro.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    @Override
    public Usuario save(UsuarioDto usuarioDto) {
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

        return usuarioRepository.save(usuario);
    }

    @Override
    public Boolean ativaDesativaUsuario(Long id, Boolean ativar) {
        Usuario usuario = findById(id);
        usuario.setAtivo(ativar);
        usuarioRepository.save(usuario);

        return true;
    }

    @Override
    public void alterarSenha(String username, String newPassword, String newPasswordRepet, String codVerificacao) {
        if(!newPassword.equals(newPasswordRepet)) new UsuarioException("As senhas são diferentes!");
        Optional<Usuario> usuarioAux = usuarioRepository.findByUsername(username);
        if(!usuarioAux.isPresent()) new UsuarioException("Usuário não encontrado!");
        Usuario usuario = usuarioAux.get();
        if(!usuario.getCodRecuperarSenha().equals(codVerificacao)) new UsuarioException("Código de verificação inválido!");
        usuario.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        usuario.setCodRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico());

        usuarioRepository.save(usuario);

    }

    @Override
    public void envairCodVerificacaoPorEmail(String email) {
        Optional<Usuario> usuarioAux = usuarioRepository.findByEmail(email);
        if(!usuarioAux.isPresent()) new UsuarioException("Usuário não encontrado!");
        Usuario usuario = usuarioAux.get();
        if(!usuario.isAtivo()) new UsuarioException("Usuário inativo!");
        usuario.setCodRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico());
        //enviar o código para o e-mail

        usuarioRepository.save(usuario);
    }
}
