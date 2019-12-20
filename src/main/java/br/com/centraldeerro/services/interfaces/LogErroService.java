package br.com.centraldeerro.services.interfaces;

import br.com.centraldeerro.dto.LogErroDto;
import br.com.centraldeerro.entities.LogErro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.rmi.runtime.Log;

import java.util.List;

public interface LogErroService {

    LogErro save(LogErroDto logErroDto);

    Page<LogErro> findAll(Pageable pageable);

    Page<LogErro> findArquivado(Pageable pageable, Boolean arquivado);

    Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro);

    Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, Long tipoErro, Boolean arquivado);

    Page<LogErro> findBySistemaOperacional(Pageable pageable, String sistemaOperacional);

    Page<LogErro> findByPlataformaOrigemErro(Pageable pageable, String plataformaOrigemErro);

    LogErro findById(Long id);

    void delete(Long id);

    void arquivar(Long id, Boolean arquivar);

    void arquivarList(List<Long> ids, Boolean arquivar);


}
