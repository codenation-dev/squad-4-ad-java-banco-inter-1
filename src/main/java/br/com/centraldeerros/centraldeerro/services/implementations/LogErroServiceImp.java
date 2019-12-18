package br.com.centraldeerros.centraldeerro.services.implementations;

import br.com.centraldeerros.centraldeerro.dto.LogErroDto;
import br.com.centraldeerros.centraldeerro.entities.LogErro;
import br.com.centraldeerros.centraldeerro.exceptions.ResourceNotFoundException;
import br.com.centraldeerros.centraldeerro.repositories.LogErroRepository;
import br.com.centraldeerros.centraldeerro.services.interfaces.LogErroService;
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
        LogErro logErro = new LogErro();
        LocalDateTime localDate = LocalDateTime.now();

        logErro.setArquivado(false);
        logErro.setDataHoraCriacao(Date.from(localDate.atZone(ZoneId.of("America/Sao_Paulo")).toInstant()));

        logErro.setTipoErro(logErroDto.getTipoErro());
        logErro.setVersaoSO(logErroDto.getVersaoSO());
        logErro.setVersaoPlataforma(logErroDto.getVersaoPlataforma());
        logErro.setSistemaOperacional(logErroDto.getSistemaOperacional());
        logErro.setPlataformaOrigemErro(logErroDto.getPlataformaOrigemErro());
        logErro.setErro(logErroDto.getErro());
        logErro.setDescricao(logErroDto.getDescricao());
        logErro.setClassificacao(logErroDto.getClassificacao());
        logErro.setDataHoraErro(logErroDto.getDataHoraErro());

        return logErroRepository.save(logErro);
    }

    @Override
    public Page<LogErro> findAll(Pageable pageable) {
        return logErroRepository.findAll(pageable);
    }

    @Override
    public Page<LogErro> findArquivado(Pageable pageable, Boolean arquivado) {
        return logErroRepository.findByArquivado(pageable, arquivado);
    }

    @Override
    public Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro) {
        return logErroRepository.findByTipoErroAndArquivado(pageable, tipoErro, Boolean.valueOf(false));
    }


    @Override
    public LogErro findById(Long id) {
        return logErroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log de Erro não encontrado"));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logErroRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void arquivar(Long id) {
        LogErro logErro = findById(id);
        logErro.setArquivado(true);
        logErroRepository.save(logErro);
    }

    @Transactional
    @Override
    public void arquivarList(List<Long> ids) {
        ids.forEach(this::arquivar);
    }
}
