package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.dto.LogErroDto;
import br.com.centraldeerros.centraldeerro.entities.LogErro;
import br.com.centraldeerros.centraldeerro.entities.Usuario;
import br.com.centraldeerros.centraldeerro.services.interfaces.LogErroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/log-erro")
public class LogErroController {

    private LogErroService logErroService;

    @Autowired
    public LogErroController(LogErroService logErroService) {
        this.logErroService = logErroService;
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Salvar Log Erro", response = LogErro[].class)
    public ResponseEntity<LogErro> save(@Valid @RequestBody LogErroDto logErroDto, @RequestHeader(name = "Authorization") String token){
        logErroDto.setToken(token.split(" ")[1]);

        Authentication authentication = SecurityContextHolder.getContext()
                                            .getAuthentication();

        Usuario usuario = (Usuario) authentication.getPrincipal();

        logErroDto.setNomeUsuario(usuario.getNome());
        logErroDto.setNickName(usuario.getUsername());

        LogErro logErroSaved = logErroService.save(logErroDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(logErroSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(logErroSaved);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Erro por Id", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<LogErro> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.logErroService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(required = false, defaultValue = "tipoErro") String order){
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Order.desc(order)));

        return ResponseEntity.ok(this.logErroService.findAll(pageable));
    }

    @GetMapping("/Arquivados")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros Arquivados (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findArquivados(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(required = false, defaultValue = "20") Integer size,
                                                        @RequestParam(required = false, defaultValue = "tipoErro") String order){

        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Order.desc(order)));
        return ResponseEntity.ok(this.logErroService.findArquivado(pageable, Boolean.TRUE));
    }

    @GetMapping("/notArquivados")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros Não Arquivados (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findNotArquivados(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "20") Integer size,
                                                           @RequestParam(required = false, defaultValue = "tipoErro") String order){

        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Order.desc(order)));
        return ResponseEntity.ok(this.logErroService.findArquivado(pageable, Boolean.FALSE));
    }

    @GetMapping("/tipoErro/{tipoErro}?arq={arquivado}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Erros Paginados por Tipo de Erro", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findByTipoErroAndArquivado(@PathVariable("tipoErro") Long tipoErro,
                                                                    @PathVariable("arquivado") Boolean arquivado,
                                                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "20") Integer size,
                                                                    @RequestParam(required = false, defaultValue = "tipoErro") String order){

        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Order.desc(order)));
        return ResponseEntity.ok(this.logErroService.findByTipoErroAndArquivado(pageable, tipoErro, arquivado));
    }

    @GetMapping("/tipoErro/{tipoErro}")
    @ApiOperation(value = "Retorna Erros Paginados por Tipo de Erro", response = LogErro[].class)
    @Transactional
    public ResponseEntity<Page<LogErro>> findByTipoErro(@PathVariable("tipoErro") Long tipoErro,
                                                        @RequestParam(required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(required = false, defaultValue = "20") Integer size,
                                                        @RequestParam(required = false, defaultValue = "tipoErro") String order){
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Order.desc(order)));
        return ResponseEntity.ok(logErroService.findByTipoErro(pageable, tipoErro));
    }

    /*
    @ApiOperation(value = "Arquivar erro.", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@RequestParam Long id){
        logErroService.arquivar(id);
        return ResponseEntity.noContent().build();
    }
     */

    @ApiOperation(value = "Arquivar Um ou vários Erros", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@RequestParam(required = false, defaultValue = "0") Long id,
                                               @RequestParam(required = false) List<Long> ids){

        if(id > 0) {
            logErroService.arquivar(id);
            return ResponseEntity.noContent().build();
        }
        if(!(Objects.isNull(ids) || ids.size() <= 0)) {
            logErroService.arquivarList(ids);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}
