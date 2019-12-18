package br.com.centraldeerros.centraldeerro.repositories;

import br.com.centraldeerros.centraldeerro.entities.LogErro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface LogErroRepository extends JpaRepository<LogErro, Long> {

    Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, Long tipoErro, Boolean arquivado);

    Page<LogErro> findByArquivado(Pageable pageable, Boolean arquivado);

    Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro);

    //@Modifying
    //@Query("UPDATE log_erro l SET l.tipoErro = ?1 where l.id IN ?2")
    //void updateLogErroArquivadoById(Long tipoErro, Set<Long> id);


}