package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.entities.ErroProducao;
import br.com.centraldeerros.centraldeerro.services.ErroBaseService;
import br.com.centraldeerros.centraldeerro.services.ErroServiceProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/erros/prod")
public class ErroControllerProd {
    private ErroBaseService<ErroProducao> erroServiceProd;

    @Autowired
    public ErroControllerProd(ErroBaseService<ErroProducao> erroServiceProd){
        this.erroServiceProd = erroServiceProd;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ErroDesenvolvimento> save(@RequestBody ErroProducao erro){
        ErroProducao erroSalvo = erroServiceProd.save(erro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(erroSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroProducao> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroServiceProd.findById(id).get());
    }
}
