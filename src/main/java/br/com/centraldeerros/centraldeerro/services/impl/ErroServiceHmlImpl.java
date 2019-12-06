package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.ErroHomologacao;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.services.ErroServiceHml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErroServiceHmlImpl extends ErroBaseServiceImpl<ErroHomologacao> implements ErroServiceHml {

    @Autowired
    public ErroServiceHmlImpl(ErroBaseRepository<ErroHomologacao> erroRepository) {
        super(erroRepository);
    }
}
