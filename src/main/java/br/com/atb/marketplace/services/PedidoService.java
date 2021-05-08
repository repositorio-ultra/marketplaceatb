package br.com.atb.marketplace.services;

import br.com.atb.marketplace.entities.Pedido;
import br.com.atb.marketplace.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAll(){
       return repository.findAll();
    }

    public Pedido findById(Long id){

        // Um objeto opcional porque a busca pode retornar vazia
        Optional<Pedido> obj;
        obj = repository.findById(id);
        return obj.get() ; // vai pegar o objeto tipo Pedido que estiver na variável obj
    }
}
