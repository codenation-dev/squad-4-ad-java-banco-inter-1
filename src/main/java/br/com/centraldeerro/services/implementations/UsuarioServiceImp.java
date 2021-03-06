package br.com.centraldeerro.services.implementations;

import br.com.centraldeerro.Utils.GeradorNumeroAleatorio;
import br.com.centraldeerro.models.entities.builders.UsuarioBuilder;
import br.com.centraldeerro.models.entities.dto.UsuarioDto;
import br.com.centraldeerro.models.entities.Usuario;
import br.com.centraldeerro.exceptions.ResourceNotFoundException;
import br.com.centraldeerro.repositories.UsuarioRepository;
import br.com.centraldeerro.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
        return usuarioRepository.save(UsuarioBuilder.CriaUsuarioAllParameters(usuarioDto));
    }

    @Override
    public Boolean ativaDesativaUsuario(Long id, Boolean ativar) {
        Usuario usuario = findById(id);
        usuario.setAtivo(ativar);
        usuarioRepository.save(usuario);

        return true;
    }

    @Override
    public String alterarSenha(String username, String newPassword,
                               String newPasswordRepet, String codVerificacao) throws RuntimeException{
        try {
            if (!newPassword.equals(newPasswordRepet)) return "As senhas são diferentes!";
            Optional<Usuario> usuarioAux = usuarioRepository.findByUsername(username);
            if (!usuarioAux.isPresent()) return "Usuário não encontrado!";
            Usuario usuario = usuarioAux.get();
            if (!usuario.getCodRecuperarSenha().equals(codVerificacao))
                return "Código de verificação inválido!";
            usuario.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            usuario.setCodRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico());

            usuarioRepository.save(usuario);
            return "Alterado com sucesso!";
        }catch (Exception ex){
            return "Não foi possível alterar a senha, tente novamente mais tarde.";
        }
    }

    @Override
    public String envairCodVerificacaoPorEmail(String email){
        try {

            List<String> texto = new ArrayList<>();

            Optional<Usuario> usuarioAux = usuarioRepository.findByEmail(email);
            if (!usuarioAux.isPresent()) return "Usuário não encontrado!";
            Usuario usuario = usuarioAux.get();
            if (!usuario.isAtivo()) return "Usuário inativo!";
            usuario.setCodRecuperarSenha(GeradorNumeroAleatorio.gerarNumeroRandomico());
            //enviar o código para o e-mail
            EmailServiceImp emailSImp = new EmailServiceImp();

            texto.add("Olá " + usuario.getNome());
            texto.add(" ");
            texto.add("Para alterar a senha utilize o código de verificação abaixo.");
            texto.add("Código: " + usuario.getCodRecuperarSenha());
            texto.add(" ");
            texto.add("App Controle de Erros - Testes.");

            usuarioRepository.save(usuario);
            if (emailSImp.enviar(texto, usuario.getEmail(), "Recuperação de Senha") ==
                    Boolean.FALSE)
                return "Não foi possível enviar o e-mail, tente novamente mais tarde!";


            return "E-mail enviado! Confira sua caixa de mensagem.";
        }catch (Exception ex){
            return "Não foi possível enviar o e-mail, tente novamente mais tarde!";
        }
    }
}