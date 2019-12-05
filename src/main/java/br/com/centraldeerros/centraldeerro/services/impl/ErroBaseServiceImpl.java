package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import br.com.centraldeerros.centraldeerro.repositories.ErroBaseRepository;
import br.com.centraldeerros.centraldeerro.services.ErroBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ErroBaseServiceImpl<T extends Erro> implements ErroBaseService<T> {

    private ErroBaseRepository<T> erroRepository;
    private T tipo;

    @Autowired
    public ErroBaseServiceImpl(ErroBaseRepository<T> erroRepository){
        this.erroRepository = erroRepository;
    }

    @Override
    @Transactional
    public Optional<T> findById(Long id){
        return erroRepository.findById(id);
    }

    @Override
    @Transactional
    public List<T> findByLevel(String level){
        //String ambiente = tipo.getClass().getName();

        return erroRepository.findByLevel(level);
    }


    @Override
    @Transactional
    public Long getQuantidadeEvento(){
        return erroRepository.getQuantidadeEvento();
    }

    @Override
    @Transactional
    public List<T> findByDetalhes(String detalhes){
        //String ambiente = tipo.getClass().getName();
        return erroRepository.findByDetalhes(detalhes);
    }

    @Override
    @Transactional
    public List<T> findByOrigem(String origem){
        //String ambiente = tipo.getClass().getName();
        return erroRepository.findByOrigem(origem);
    }

    @Override
    @Transactional
    public T save(T erro){
        return erroRepository.save(erro);
    }

    @Override
    @Transactional
    public void update(Long id, T erro){
        T erroUpdate = findById(id).get();

        erroUpdate.update(erro);

        erroRepository.save(erroUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id){
        erroRepository.deleteById(id);
    }
}
