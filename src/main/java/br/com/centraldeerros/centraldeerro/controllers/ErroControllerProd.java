package br.com.centraldeerros.centraldeerro.controllers;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.entities.ErroProducao;
import br.com.centraldeerros.centraldeerro.services.ErroServiceProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/erros/prod")
@Component
@Profile("prod")
public class ErroControllerProd {
    private ErroServiceProd erroServiceProd;

    @Autowired
    public ErroControllerProd(ErroServiceProd erroServiceProd){
        this.erroServiceProd = erroServiceProd;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ErroProducao> save(@Valid @RequestBody ErroProducao erro,  @RequestHeader(name = "Authorization") String token){
        erro.changeToken(token);
        ErroProducao erroSalvo = erroServiceProd.save(erro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(erroSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(erroSalvo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ErroProducao erro){
        erroServiceProd.update(id, erro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        erroServiceProd.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ErroProducao> findById(@PathVariable Long id){
        return ResponseEntity.ok(erroServiceProd.findById(id).get());
    }

    @GetMapping("/level")
    @Transactional
    public ResponseEntity<List<ErroProducao>> findByLevel(@RequestBody String level){
        return ResponseEntity.ok(erroServiceProd.findByLevel(level));
    }

    @GetMapping("/detalhes")
    @Transactional
    public ResponseEntity<List<ErroProducao>> findByDetalhes(@RequestBody String detalhes){
        return ResponseEntity.ok(erroServiceProd.findByDetalhes(detalhes));
    }

    @GetMapping("/origem")
    @Transactional
    public ResponseEntity<List<ErroProducao>> findByOrigem(@RequestBody String findByOrigem){
        return ResponseEntity.ok(erroServiceProd.findByOrigem(findByOrigem));
    }
}
