package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.entities.ErroHomologacao;
import br.com.centraldeerros.centraldeerro.services.ErroServiceHml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/erros/hml")
public class ErroControllerHml {

    private ErroServiceHml erroBaseServiceHml;

    @Autowired
    public ErroControllerHml(ErroServiceHml erroBaseServiceHml){
        this.erroBaseServiceHml = erroBaseServiceHml;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ErroHomologacao> save(@RequestBody ErroHomologacao erro){
        ErroHomologacao erroSalvo = erroBaseServiceHml.save(erro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(erroSalvo.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroHomologacao> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroBaseServiceHml.findById(id).get());
    }

    @GetMapping
    @Transactional
    public List<ErroHomologacao> findByLevel(@RequestBody String level){
        return erroBaseServiceHml.findByLevel(level);
    }
}
