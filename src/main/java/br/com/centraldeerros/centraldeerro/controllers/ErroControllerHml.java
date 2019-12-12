package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.entities.ErroHomologacao;
import br.com.centraldeerros.centraldeerro.services.ErroServiceHml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/erros/hml")
public class ErroControllerHml {

    private ErroServiceHml erroServiceHml;

    @Autowired
    public ErroControllerHml(ErroServiceHml erroBaseServiceHml){
        this.erroServiceHml = erroBaseServiceHml;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ErroHomologacao> save(@Valid @RequestBody ErroHomologacao erro){
        ErroHomologacao erroSalvo = erroServiceHml.save(erro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(erroSalvo.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ErroHomologacao erro){
        erroServiceHml.update(id, erro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        erroServiceHml.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroHomologacao> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroServiceHml.findById(id).get());
    }

    @GetMapping("/level")
    @Transactional
    public ResponseEntity<List<ErroHomologacao>> findByLevel(@RequestBody String level){
        return ResponseEntity.ok(erroServiceHml.findByLevel(level));
    }

    @GetMapping("/detalhes")
    @Transactional
    public ResponseEntity<List<ErroHomologacao>> findByDetalhes(@RequestBody String detalhes){
        return ResponseEntity.ok(erroServiceHml.findByDetalhes(detalhes));
    }

    @GetMapping("/origem")
    @Transactional
    public ResponseEntity<List<ErroHomologacao>> findByOrigem(@RequestBody String findByOrigem){
        return ResponseEntity.ok(erroServiceHml.findByOrigem(findByOrigem));
    }
    
}
