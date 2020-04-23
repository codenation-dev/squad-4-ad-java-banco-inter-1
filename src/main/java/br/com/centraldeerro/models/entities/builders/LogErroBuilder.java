package br.com.centraldeerro.models.entities.builders;

import br.com.centraldeerro.models.entities.LogErro;
import br.com.centraldeerro.models.entities.dto.LogErroDto;
import sun.rmi.runtime.Log;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LogErroBuilder {
    public static LogErro CriarLogErroAllParameters(LogErroDto logErroDto){
        LogErro logErro = new LogErro();
        LocalDateTime localDate = LocalDateTime.now();

        return logErro.builder()
                .arquivado(false)
                .dataHoraCriacao(Date.from(localDate.atZone(ZoneId.of("America/Sao_Paulo")).toInstant()))
                .nomeUsuario(logErroDto.getNomeUsuario())
                .nickName(logErroDto.getNickName())
                .tipoErro(logErroDto.getTipoErro())
                .versaoSO(logErroDto.getVersaoSO())
                .versaoPlataforma(logErroDto.getVersaoPlataforma())
                .sistemaOperacional(logErroDto.getSistemaOperacional())
                .plataformaOrigemErro(logErroDto.getPlataformaOrigemErro())
                .erro(logErroDto.getErro())
                .descricao(logErroDto.getDescricao())
                .dataHoraErro(logErroDto.getDataHoraErro())
                .token(logErroDto.getToken())
                .ip(logErroDto.getIp())
                .build();
    }
}
