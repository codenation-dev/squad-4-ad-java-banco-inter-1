package br.com.centraldeerro.services.interfaces;

import br.com.centraldeerro.dto.UsuarioDto;
import br.com.centraldeerro.entities.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);

    Usuario save(UsuarioDto usuario);

    Boolean ativaDesativaUsuario(Long id, Boolean ativar);

    void alterarSenha(String username, String newPassword, String newPasswordRepet, String codVerificacao);

    void envairCodVerificacaoPorEmail(String email);
}
