package br.com.centraldeerro.services.interfaces;

import br.com.centraldeerro.models.entities.dto.UsuarioDto;
import br.com.centraldeerro.models.entities.Usuario;

public interface UsuarioService {

    Usuario findById(Long id);

    Usuario save(UsuarioDto usuario);

    Boolean ativaDesativaUsuario(Long id, Boolean ativar);

    String alterarSenha(String username, String newPassword, String newPasswordRepet, String codVerificacao);

    String envairCodVerificacaoPorEmail(String email);
}
