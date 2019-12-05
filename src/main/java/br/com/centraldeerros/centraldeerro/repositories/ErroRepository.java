package br.com.centraldeerros.centraldeerro.repositories;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErroRepository extends JpaRepository<Erro, Long> {

    Optional<Erro> findById(Long id);

    // # implementar pageable
    List<Erro> findByLevel(String level);

    @Query("SELECT count(e.quantidadeDeEventos) FROM Erro e")
    Long getQuantidadeEvento();

    // # implementar pageable
    @Query("SELECT e FROM Erro e WHERE e.detalhes = :detalhes")
    List<Erro> findByDetalhes(String detalhes);

    // # implementar pageable
    @Query("SELECT e FROM Erro e WHERE e.origem = :origem")
    List<Erro> findByOrigem(String origem);

}
