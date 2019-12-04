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
    public Optional<Erro> findById(Long id){
        return erroRepository.findById(id);
    }

    @Override
    public List<Erro> findByLevel(String level){
        return erroRepository.findByLevel(level);
    }


    @Override
    public Long getQuantidadeEvento(){
        return erroRepository.getQuantidadeEvento();
    }

    @Override
    public List<Erro> findByDescricao(String descricao){
        return erroRepository.findByDescricao(descricao);
    }

    @Override
    public List<Erro> findByOrigem(String origem){
        return erroRepository.findByOrigem(origem);
    }

    @Override
    public Optional<Erro> save(Erro erro){
        return erroRepository.save(erro);
    }

    @Override
    public void update(Long id, Erro erro){
        Erro erroUpdate = findById(id).get();

        erroUpdate.update(erro);

        erroRepository.save(erroUpdate);
    }

    @Override
    public void delete(Long id){
        erroRepository.deleteById(id);
    }

}
