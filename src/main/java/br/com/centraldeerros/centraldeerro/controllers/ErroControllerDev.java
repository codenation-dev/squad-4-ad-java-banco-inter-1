package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.services.ErroBaseService;
import br.com.centraldeerros.centraldeerro.services.ErroServiceDev;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;


@RestController
@RequestMapping("/erros/dev")
@Component
public class ErroControllerDev {

    private ErroBaseService<ErroDesenvolvimento> erroServiceDev;

    @Autowired
    public ErroControllerDev(ErroBaseService<ErroDesenvolvimento> erroServiceDev){
        this.erroServiceDev = erroServiceDev;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ErroDesenvolvimento> save(@RequestBody ErroDesenvolvimento erro){
        ErroDesenvolvimento erroSalvo = erroServiceDev.save(erro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(erroSalvo.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroDesenvolvimento> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroServiceDev.findById(id).get());
    }
}
