package br.com.centraldeerro.services.implementations;

import br.com.centraldeerro.dto.LogErroDto;
import br.com.centraldeerro.entities.LogErro;
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
        LogErro logErro = new LogErro();
        LocalDateTime localDate = LocalDateTime.now();

        logErro.setArquivado(false);
        logErro.setDataHoraCriacao(Date.from(localDate.atZone(ZoneId.of("America/Sao_Paulo")).toInstant()));
        logErro.setNomeUsuario(logErroDto.getNomeUsuario());
        logErro.setNickName(logErroDto.getNickName());

        logErro.setTipoErro(logErroDto.getTipoErro());
        logErro.setVersaoSO(logErroDto.getVersaoSO());
        logErro.setVersaoPlataforma(logErroDto.getVersaoPlataforma());
        logErro.setSistemaOperacional(logErroDto.getSistemaOperacional());
        logErro.setPlataformaOrigemErro(logErroDto.getPlataformaOrigemErro());
        logErro.setErro(logErroDto.getErro());
        logErro.setDescricao(logErroDto.getDescricao());
        logErro.setDataHoraErro(logErroDto.getDataHoraErro());
        logErro.setToken(logErroDto.getToken());
        logErro.setIp(logErroDto.getIp());

        return logErroRepository.save(logErro);
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
    public Page<LogErro> findByTipoErro(Pageable pageable, Long tipoErro) {
        return logErroRepository.findByTipoErro(pageable, tipoErro);
    }

    @Transactional
    @Override
    public Page<LogErro> findByTipoErroAndArquivado(Pageable pageable, Long tipoErro, Boolean arquivado){
        return logErroRepository.findByTipoErroAndArquivado(pageable, tipoErro,arquivado);
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
