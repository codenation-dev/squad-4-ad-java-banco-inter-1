package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.ErroHomologacao;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.services.ErroServiceHml;


public abstract class ErroServiceHmlImpl extends ErroBaseServiceImpl<ErroHomologacao> implements ErroServiceHml {

    public ErroServiceHmlImpl(ErroBaseRepository<ErroHomologacao> erroRepository) {
        super(erroRepository);
    }
}
