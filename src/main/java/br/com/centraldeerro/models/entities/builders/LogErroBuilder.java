package br.com.centraldeerro.models.entities.builders;

import br.com.centraldeerro.models.entities.LogErro;
import br.com.centraldeerro.models.entities.dto.LogErroDto;
import sun.rmi.runtime.Log;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LogErroBuilder {
    public static LogErro CriarLogErro(LogErroDto logErroDto){
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

        return logErro;
    }
}
