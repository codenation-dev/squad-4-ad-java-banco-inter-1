package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.services.ErroServiceDev;
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

    private ErroServiceDev erroServiceDev;

    @Autowired
    public ErroControllerDev(ErroServiceDev erroServiceDev){
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
