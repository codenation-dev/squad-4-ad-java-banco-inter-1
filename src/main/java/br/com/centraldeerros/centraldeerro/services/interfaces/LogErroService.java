package br.com.centraldeerros.centraldeerro.services.interfaces;

import br.com.centraldeerros.centraldeerro.dto.LogErroDto;
import br.com.centraldeerros.centraldeerro.entities.LogErro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogErroService {

    LogErro save(LogErroDto logErroDto);

    Page<LogErro> findAll(Pageable pageable);

    Page<LogErro> findArquivado(Pageable pageable, Boolean arquivado);

    Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro);

    Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, Long tipoErro, Boolean arquivado);

    LogErro findById(Long id);

    void delete(Long id);

    void arquivar(Long id);

    void arquivarList(List<Long> ids);


}
