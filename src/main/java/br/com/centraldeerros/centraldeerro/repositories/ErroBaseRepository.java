package br.com.centraldeerros.centraldeerro.repositories;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface ErroBaseRepository<T extends Erro> extends JpaRepository<T,Long> {
    Optional<T> findById(Long id);

    // # implementar pageable
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.level = :level")
    List<T> findByLevel(@Param("level") String level);

    @Query(value = "SELECT count(e.quantidadeDeEventos) FROM #{#entityName} e", nativeQuery = true)
    Long getQuantidadeEvento();

    // # implementar pageable
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.detalhes = :detalhes", nativeQuery = true)
    List<T> findByDetalhes(@Param("detalhes") String detalhes);

    // # implementar pageable
    @Query(value = "SELECT e FROM Erro e WHERE e.origem = :origem", nativeQuery = true)
    List<T> findByOrigem(@Param("origem") String origem);
}
