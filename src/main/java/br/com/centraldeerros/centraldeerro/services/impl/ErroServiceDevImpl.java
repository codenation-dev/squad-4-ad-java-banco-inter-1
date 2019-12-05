package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.ErroDesenvolvimento;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.repositories.ErroRepositoryDev;
import br.com.centraldeerros.centraldeerro.services.ErroServiceDev;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public abstract class ErroServiceDevImpl extends ErroBaseServiceImpl<ErroDesenvolvimento> implements ErroServiceDev {

    public ErroServiceDevImpl(ErroRepositoryDev erroRepository) {
        super(erroRepository);
    }
}
