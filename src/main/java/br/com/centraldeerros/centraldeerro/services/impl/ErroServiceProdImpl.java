package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.ErroProducao;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.services.ErroServiceProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErroServiceProdImpl extends ErroBaseServiceImpl<ErroProducao> implements ErroServiceProd {

    @Autowired
    public ErroServiceProdImpl(ErroBaseRepository<ErroProducao> erroBaseRepository){
        super(erroBaseRepository);
    }
}
