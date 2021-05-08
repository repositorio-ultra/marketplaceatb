package br.com.atb.marketplace.services;

import br.com.atb.marketplace.entities.Produto;
import br.com.atb.marketplace.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
       return repository.findAll();
    }

    public Produto findById(Long id){

        // Um objeto opcional porque a busca pode retornar vazia
        Optional<Produto> obj;
        obj = repository.findById(id);
        return obj.get() ; // vai pegar o objeto tipo Produto que estiver na variável obj
    }
}
