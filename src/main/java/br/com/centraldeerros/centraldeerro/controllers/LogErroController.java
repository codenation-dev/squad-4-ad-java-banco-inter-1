package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.dto.LogErroDto;
import br.com.centraldeerros.centraldeerro.entities.LogErro;
import br.com.centraldeerros.centraldeerro.services.interfaces.LogErroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
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
    @ApiOperation(value = "Retorna Todos os Erros (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<LogErro> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.logErroService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findAll(@ApiIgnore @PageableDefault(sort = "id",
                                                         direction = Sort.Direction.DESC,
                                                         page = 0, size = 20) Pageable pageable){
        return ResponseEntity.ok(this.logErroService.findAll(pageable));
    }

    @GetMapping("/Arquivados")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros Arquivados (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findArquivados(@ApiIgnore @PageableDefault(sort = "id",
            direction = Sort.Direction.DESC,
            page = 0, size = 20) Pageable pageable){
        return ResponseEntity.ok(this.logErroService.findArquivado(pageable, Boolean.TRUE));
    }

    @GetMapping("/notArquivados")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Todos os Erros Não Arquivados (Paginados)", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findNotArquivados(@ApiIgnore @PageableDefault(sort = "id",
            direction = Sort.Direction.DESC,
            page = 0, size = 20) Pageable pageable){
        return ResponseEntity.ok(this.logErroService.findArquivado(pageable, Boolean.FALSE));
    }

    @GetMapping("/tipoErro/{tipoErro}?arq={arquivado}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retorna Erros Paginados por Tipo de Erro", response = LogErro[].class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Log de Erros paginados"), @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<Page<LogErro>> findByTipoErroAndArquivado(@PathVariable("tipoErro") Long tipoErro, @PathVariable("arquivado") Boolean arquivado,
                                                                    @ApiIgnore @PageableDefault(sort = "id",
                                                                            direction = Sort.Direction.DESC,
                                                                            page = 0, size = 20) Pageable pageable){
        return ResponseEntity.ok(this.logErroService.findByTipoErroAndArquivado(pageable, tipoErro, arquivado));
    }

    @GetMapping("/tipoErro/{tipoErro}")
    @Transactional
    public ResponseEntity<Page<LogErro>> findByTipoErro(@PathVariable("tipoErro") Long tipoErro,
                                                            @ApiIgnore @PageableDefault(sort = "id",
                                                                        direction = Sort.Direction.DESC,
                                                                        page = 0, size = 20) Pageable pageable){
        return ResponseEntity.ok(logErroService.findByTipoErro(pageable, tipoErro));
    }

    @ApiOperation(value = "Arquivar erro.", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> arquivar(@PathVariable Long id){
        logErroService.arquivar(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Arquivar Lista de Erros", response = LogErro[].class)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/arquivarVarios")
    public ResponseEntity<Void> arquivarVarios(@RequestParam List<Long> ids){
        logErroService.arquivarList(ids);
        return ResponseEntity.noContent().build();
    }




}
