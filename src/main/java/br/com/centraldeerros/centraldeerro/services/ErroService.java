package br.com.centraldeerros.centraldeerro.services;


import br.com.centraldeerros.centraldeerro.entities.Erro;

import java.util.List;
import java.util.Optional;

public interface ErroService {

    Optional findById(Long id);

    // # implementar pageable
    List<Erro> findByLevel(String leve);

    Long getQuantidadeEvento();

    List<Erro> findByDescricao(String descricao);

    List<Erro> findByOrigem(String origem);

    Erro save(Erro erro);

    void update(Erro erro);

    void delete(Long id);
}
