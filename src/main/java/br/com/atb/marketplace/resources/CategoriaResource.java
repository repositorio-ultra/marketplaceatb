package br.com.atb.marketplace.resources;

import br.com.atb.marketplace.entities.Categoria;
import br.com.atb.marketplace.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        //Categoria cl1 = new Categoria(1L,"Ricardo Vieira da Silva", "Ricardo","ricvsweb", "ric123",14394393892L, Instant.parse("1971-02-25T03:00:00Z"));
        List<Categoria> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }
}
