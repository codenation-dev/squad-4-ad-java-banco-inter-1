package br.com.centraldeerro.repositories;

import br.com.centraldeerro.entities.LogErro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErroRepository extends JpaRepository<LogErro, Long> {

    Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro);

    Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, Long tipoErro, Boolean arquivado);

    Page<LogErro> findBySistemaOperacional(Pageable pageable, String sistemaOperacional);

    Page<LogErro> findByArquivado(Pageable pageable, Boolean arquivado);

    Page<LogErro> findByPlataformaOrigemErro(Pageable pageable, String plataformaOrigemErro);

    //@Modifying
    //@Query("UPDATE log_erro l SET l.tipoErro = ?1 where l.id IN ?2")
    //void updateLogErroArquivadoById(Long tipoErro, Set<Long> id);


}