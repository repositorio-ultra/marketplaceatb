package br.com.atb.marketplace.resources;

import br.com.atb.marketplace.entities.Produto;
import br.com.atb.marketplace.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        //Produto cl1 = new Produto(1L,"Ricardo Vieira da Silva", "Ricardo","ricvsweb", "ric123",14394393892L, Instant.parse("1971-02-25T03:00:00Z"));
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }
}
