package br.com.centraldeerros.centraldeerro.services.interfaces;

import br.com.centraldeerros.centraldeerro.dto.UsuarioDto;
import br.com.centraldeerros.centraldeerro.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario findById(Long id);

    UsuarioDto save(UsuarioDto usuario);

    Boolean ativaDesativaUsuario(Long id, Boolean ativar);

    void alterarSenha(String username, String newPassword, String newPasswordRepet, String codVerificacao);

    void envairCodVerificacaoPorEmail(String email);
}