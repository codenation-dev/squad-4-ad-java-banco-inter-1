package br.com.centraldeerros.centraldeerro.repositories;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErroRepository extends JpaRepository<Erro, Long> {

    Optional<Erro> findById(Long id);

    // # implementar pageable
    List<Erro> findByLevel(String level);

    Long getQuantidadeEvento();

    // # implementar pageable
    List<Erro> findByDescricao(String descricao);

    // # implementar pageable
    List<Erro> findByOrigem(String origem);

}
