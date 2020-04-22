package br.com.centraldeerro.controllers;

import br.com.centraldeerro.models.entities.dto.UsuarioDto;
import br.com.centraldeerro.models.entities.Usuario;
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
    @ApiOperation(value = "Salvar Usuário", response = Usuario.class)
    public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.save(usuarioDto));
    }

    @ApiOperation(value = "Ativa/Desativar usuário.", response = Usuario.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/enableDesableUser")
    public ResponseEntity<Boolean> desativar(@RequestParam Long id, @RequestParam Boolean ativo){
        return ResponseEntity.ok(usuarioService.ativaDesativaUsuario(id,ativo));
    }

    @ApiOperation(value = "Alterar senha.", response = Usuario.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changePassword")
    public ResponseEntity<String> alterarSenha(@RequestParam String username,
                                               @RequestParam String newPassword,
                                               @RequestParam String newPasswordRepet,
                                               @RequestParam String codVerificacao){
        return ResponseEntity.ok(usuarioService.alterarSenha(username, newPassword, newPasswordRepet, codVerificacao));

    }

    @ApiOperation(value = "Enviar código de alteração por e-mail.", response = Usuario.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/sendCodAlterPassword")
    public ResponseEntity<String> enviarCodVerificacaoPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.envairCodVerificacaoPorEmail(email));
    }
}
