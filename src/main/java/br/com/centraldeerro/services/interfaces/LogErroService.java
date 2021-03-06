package br.com.centraldeerro.services.interfaces;

import br.com.centraldeerro.models.entities.dto.LogErroDto;
import br.com.centraldeerro.models.entities.LogErro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogErroService {

    LogErro save(LogErroDto logErroDto);

    Page<LogErro> findAll(Pageable pageable);

    Page<LogErro> findArquivado(Pageable pageable, Boolean arquivado);

    Page<LogErro> findByTipoErro(Pageable pageable, String tipoErro);

    Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, String tipoErro, Boolean arquivado);

    Page<LogErro> findBySistemaOperacional(Pageable pageable, String sistemaOperacional);

    Page<LogErro> findByPlataformaOrigemErro(Pageable pageable, String plataformaOrigemErro);

    LogErro findById(Long id);

    void delete(Long id);

    void arquivar(Long id, Boolean arquivar);

    void arquivarList(List<Long> ids, Boolean arquivar);


}
