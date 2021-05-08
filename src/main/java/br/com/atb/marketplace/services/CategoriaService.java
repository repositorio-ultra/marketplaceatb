package br.com.atb.marketplace.services;

import br.com.atb.marketplace.entities.Categoria;
import br.com.atb.marketplace.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll(){
       return repository.findAll();
    }

    public Categoria findById(Long id){

        // Um objeto opcional porque a busca pode retornar vazia
        Optional<Categoria> obj;
        obj = repository.findById(id);
        return obj.get() ; // vai pegar o objeto tipo Categoria que estiver na variável obj
    }
}
