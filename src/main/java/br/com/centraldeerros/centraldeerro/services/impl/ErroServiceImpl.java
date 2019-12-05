package br.com.centraldeerros.centraldeerro.services.impl;

import br.com.centraldeerros.centraldeerro.entities.Erro;
import br.com.centraldeerros.centraldeerro.repositories.ErroRepository;
import br.com.centraldeerros.centraldeerro.services.ErroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ErroServiceImpl implements ErroService {

    private ErroRepository erroRepository;

    @Autowired
    public ErroServiceImpl(ErroRepository erroRepository){
        this.erroRepository = erroRepository;
    }

    @Override
    @Transactional
    public Optional<Erro> findById(Long id){
        return erroRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Erro> findByLevel(String level){
        return erroRepository.findByLevel(level);
    }


    @Override
    @Transactional
    public Long getQuantidadeEvento(){
        return erroRepository.getQuantidadeEvento();
    }

    @Override
    @Transactional
    public List<Erro> findByDetalhes(String detalhes){
        return erroRepository.findByDetalhes(detalhes);
    }

    @Override
    @Transactional
    public List<Erro> findByOrigem(String origem){
        return erroRepository.findByOrigem(origem);
    }

    @Override
    @Transactional
    public Erro save(Erro erro){
        return erroRepository.save(erro);
    }

    @Override
    @Transactional
    public void update(Long id, Erro erro){
        Erro erroUpdate = findById(id).get();

        erroUpdate.update(erro);

        erroRepository.save(erroUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id){
        erroRepository.deleteById(id);
    }

}
