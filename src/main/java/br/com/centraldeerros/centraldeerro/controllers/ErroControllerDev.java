package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.services.ErroServiceDev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


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
    public ResponseEntity<ErroDesenvolvimento> save(@Valid @RequestBody ErroDesenvolvimento erro){
        ErroDesenvolvimento erroSalvo = erroServiceDev.save(erro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(erroSalvo.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ErroDesenvolvimento erro){
        erroServiceDev.update(id, erro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        erroServiceDev.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroDesenvolvimento> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroServiceDev.findById(id).get());
    }

    @GetMapping("/level")
    @Transactional
    public ResponseEntity<List<ErroDesenvolvimento>> findByLevel(@RequestBody String level){
        return ResponseEntity.ok(this.erroServiceDev.findByLevel(level));
    }

    @GetMapping("/detalhes")
    @Transactional
    public ResponseEntity<List<ErroDesenvolvimento>> findByDetalhes(@RequestBody String detalhes){
        return ResponseEntity.ok(erroServiceDev.findByDetalhes(detalhes));
    }

    @GetMapping("/origem")
    @Transactional
    public ResponseEntity<List<ErroDesenvolvimento>> findByOrigem(@RequestBody String findByOrigem){
        return ResponseEntity.ok(erroServiceDev.findByOrigem(findByOrigem));
    }


}
