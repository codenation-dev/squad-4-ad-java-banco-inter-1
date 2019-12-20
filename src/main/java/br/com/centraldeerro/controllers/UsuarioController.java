package br.com.centraldeerro.controllers;

import br.com.centraldeerro.dto.UsuarioDto;
import br.com.centraldeerro.entities.Usuario;
import br.com.centraldeerro.entities.LogErro;
import br.com.centraldeerro.services.interfaces.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Salvar Usuário", response = LogErro[].class)
    public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.save(usuarioDto));
    }

    @ApiOperation(value = "Ativa/Desativar usuário.", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/enableDesableUser")
    public ResponseEntity<Boolean> desativar(@RequestParam Long id, @RequestParam Boolean ativo){
        return ResponseEntity.ok(usuarioService.ativaDesativaUsuario(id,ativo));
    }

    @ApiOperation(value = "Alterar senha.", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changePassword")
    public ResponseEntity<Void> alterarSenha(@RequestParam String username,
                                             @RequestParam String newPassword,
                                             @RequestParam String newPasswordRepet,
                                             @RequestParam String codVerificacao){
        usuarioService.alterarSenha(username, newPassword, newPasswordRepet, codVerificacao);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Enviar código de alteração por e-mail.", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/sendCodAlterPassword")
    public ResponseEntity<Void> enviarCodVerificacaoPorEmail(@RequestParam String email){
        usuarioService.envairCodVerificacaoPorEmail(email);
        return ResponseEntity.noContent().build();
    }
}
