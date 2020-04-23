package br.com.centraldeerro.services.implementations;

import br.com.centraldeerro.models.entities.builders.LogErroBuilder;
import br.com.centraldeerro.models.entities.dto.LogErroDto;
import br.com.centraldeerro.models.entities.LogErro;
import br.com.centraldeerro.exceptions.ResourceNotFoundException;
import br.com.centraldeerro.repositories.LogErroRepository;
import br.com.centraldeerro.services.interfaces.LogErroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class LogErroServiceImp implements LogErroService {

    private final LogErroRepository logErroRepository;

    @Autowired
    public LogErroServiceImp(LogErroRepository logErroRepository) {
        this.logErroRepository = logErroRepository;
    }

    @Transactional
    @Override
    public LogErro save(LogErroDto logErroDto) {
        return logErroRepository.save(LogErroBuilder.CriarLogErroAllParameters(logErroDto));
    }

    @Transactional
    @Override
    public LogErro findById(Long id) {
        return logErroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log de Erro não encontrado"));
    }


    @Transactional
    @Override
    public Page<LogErro> findAll(Pageable pageable) {
        return logErroRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<LogErro> findByTipoErro(Pageable pageable, String tipoErro) {
        return logErroRepository.findByTipoErro(pageable, tipoErro);
    }

    @Transactional
    @Override
    public Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, String tipoErro, Boolean arquivado){
        return logErroRepository.findByTipoErroAndArquivado(pageable, tipoErro, arquivado);
    }

    @Transactional
    @Override
    public Page<LogErro> findBySistemaOperacional(Pageable pageable, String sistemaOperacional){
        return logErroRepository.findBySistemaOperacional(pageable, sistemaOperacional);
    }

    @Transactional
    @Override
    public Page<LogErro> findByPlataformaOrigemErro(Pageable pageable, String plataformaOrigemErro){
        return logErroRepository.findByPlataformaOrigemErro(pageable, plataformaOrigemErro);
    }

    @Transactional
    @Override
    public Page<LogErro> findArquivado(Pageable pageable, Boolean arquivado) {
        return logErroRepository.findByArquivado(pageable, arquivado);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logErroRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void arquivar(Long id, Boolean arquivar) {
        LogErro logErro = findById(id);
        logErro.setArquivado(arquivar);
        logErroRepository.save(logErro);
    }

    @Transactional
    @Override
    public void arquivarList(List<Long> ids, Boolean arquivar) {
        ids.forEach(id  -> arquivar(id,arquivar));
    }


}
