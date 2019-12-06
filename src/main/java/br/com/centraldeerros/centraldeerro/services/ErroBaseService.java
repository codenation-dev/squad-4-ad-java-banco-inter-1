package br.com.centraldeerros.centraldeerro.services;

import br.com.centraldeerros.centraldeerro.entities.Erro;

import java.util.List;
import java.util.Optional;


public interface ErroBaseService<T extends Erro> {

    Optional<T> findById(Long id);

    // # implementar pageable
    List<T> findByLevel(String leve);

    Long getQuantidadeEvento();

    List<T> findByDetalhes(String detalhes);

    List<T> findByOrigem(String origem);

    T save(T erro);

    void update(Long id, T erro);

    void delete(Long id);
}
