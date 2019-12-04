package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import br.com.centraldeerros.centraldeerro.repositories.ErroRepository;
import br.com.centraldeerros.centraldeerro.services.ErroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ErroServiceImpl implements ErroService {

    private ErroRepository erroRepository;

    @Override
    public Optional findById(Long id){
        return null;
    }

    @Override
    public List<Erro> findByLevel(String level){
        return null;
    }


    @Override
    public Long getQuantidadeEvento(){
        return null;
    }

    @Override
    public List<Erro> findByDescricao(String descricao){
        return null;
    }

    @Override
    public List<Erro> findByOrigem(String origem){
        return null;
    }

    @Override
    public Optional<Erro> save(Erro erro){
        return null;
    }

    @Override
    public void update(Erro erro){

    }

    @Override
    public void delete(Long id){

    }

}
