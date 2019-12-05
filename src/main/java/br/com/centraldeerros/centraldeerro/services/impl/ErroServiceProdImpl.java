package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.ErroProducao;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.services.ErroServiceProd;

public abstract class ErroServiceProdImpl extends ErroBaseServiceImpl<ErroProducao> implements ErroServiceProd {
    public ErroServiceProdImpl(ErroBaseRepository<ErroProducao> erroBaseRepository){
        super(erroBaseRepository);
    }
}
